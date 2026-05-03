import { useEffect, useState } from "react";
import API from "../api/axios";
import { useNavigate } from "react-router-dom";

export default function Projects() {

  const [projects, setProjects] = useState([]);
  const [name, setName] = useState("");
  const [users, setUsers] = useState([]);
  const [selectedUsers, setSelectedUsers] = useState({});

  const nav = useNavigate();

  const role = localStorage.getItem("role")?.replace("ROLE_", "");
  const userId = localStorage.getItem("userId");

  // ✅ FIXED useEffect
  useEffect(() => {
    if (role === "ADMIN") {
      loadProjects();
      loadUsers();
    } else {
      loadMyProjects();   // ✅ MEMBER
    }
  }, []);

  // ✅ ADMIN → all projects
  const loadProjects = async () => {
    try {
      const res = await API.get("/projects");
      setProjects(res.data);
    } catch (err) {
      console.log("error loading projects", err);
    }
  };

  // ✅ MEMBER → only assigned projects
  const loadMyProjects = async () => {
    try {
      const res = await API.get(`/projects/user/${userId}`);
      setProjects(res.data);
    } catch (err) {
      console.log("error loading member projects", err);
    }
  };

  // load users (ADMIN only)
  const loadUsers = async () => {
    try {
      const res = await API.get("/users");
      setUsers(res.data);
    } catch {
      console.log("error loading users");
    }
  };

  // create project (ADMIN)
  const createProject = async () => {
    try {
      await API.post(`/projects?userId=${userId}`, { name });
      setName("");

      loadProjects();
    } catch {
      alert("only admin can create project");
    }
  };

  // assign members (ADMIN)
  const assignMembers = async (projectId) => {
    try {
      await API.put(
        `/projects/${projectId}/members?adminId=${userId}`,
        selectedUsers[projectId] || []
      );

      alert("members assigned");
    } catch {
      alert("error assigning members");
    }
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">

      <h2 className="text-2xl font-bold mb-6 text-gray-800">
        Projects
      </h2>

      {/* ✅ ADMIN CREATE PROJECT */}
      {role === "ADMIN" && (
        <div className="flex gap-3 mb-6">

          <input
            placeholder="Enter project name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="flex-1 border p-3 rounded-lg"
          />

          <button
            onClick={createProject}
            className="bg-blue-600 text-white px-5 rounded-lg"
          >
            Add
          </button>

        </div>
      )}

      {/* PROJECT LIST */}
      <div className="space-y-4">

        {projects.length === 0 && (
          <p className="text-gray-500 text-center">
            No projects available
          </p>
        )}

        {projects.map((p) => (
          <div
            key={p.id}
            className="bg-white shadow-md rounded-xl p-4 border"
          >

            <div className="flex justify-between items-center">

              <h3 className="text-lg font-semibold text-gray-800">
                {p.name}
              </h3>

              {/* ✅ ADMIN → project tasks */}
              {role === "ADMIN" ? (
                <button
                  onClick={() => nav(`/tasks/${p.id}`)}
                  className="text-blue-600 text-sm"
                >
                  Open Tasks →
                </button>
              ) : (
                // ✅ MEMBER → go to my tasks page
                <button
                  onClick={() => nav("/my-tasks")}
                  className="text-green-600 text-sm"
                >
                  View My Tasks →
                </button>
              )}

            </div>

            {/* ✅ ADMIN ONLY → assign members */}
            {role === "ADMIN" && (
              <div className="mt-4">

                <p className="text-sm text-gray-500 mb-2">
                  Assign Members
                </p>

                <div className="grid grid-cols-2 gap-2 mb-3">

                  {users.map((u) => (
                    <label
                      key={u.id}
                      className="flex items-center gap-2 text-sm bg-gray-100 px-2 py-1 rounded"
                    >
                      <input
                        type="checkbox"
                        value={u.id}
                        onChange={(e) => {
                          const checked = e.target.checked;

                          let updated = selectedUsers[p.id] || [];

                          if (checked) {
                            updated = [...updated, u.id];
                          } else {
                            updated = updated.filter(id => id !== u.id);
                          }

                          setSelectedUsers({
                            ...selectedUsers,
                            [p.id]: updated
                          });
                        }}
                      />

                      {u.name}
                    </label>
                  ))}

                </div>

                <button
                  onClick={() => assignMembers(p.id)}
                  className="bg-green-600 text-white px-4 py-2 rounded-lg text-sm"
                >
                  Assign Members
                </button>

              </div>
            )}

          </div>
        ))}

      </div>
    </div>
  );
}