import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import API from "../api/axios";

export default function Tasks() {
  const { projectId } = useParams();

  const [tasks, setTasks] = useState([]);
  const [users, setUsers] = useState([]);

  const [title, setTitle] = useState("");
  const [assignedUser, setAssignedUser] = useState("");

  const role = localStorage.getItem("role")?.replace("ROLE_", "");
  const userId = Number(localStorage.getItem("userId"));

  //  FIXED useEffect
  useEffect(() => {
  loadTasks();

  if (role === "ADMIN") {
    loadUsers();
  }
}, [projectId, role]);
  //  LOAD TASKS
  const loadTasks = async () => {
  try {
    let res;

    if (role === "ADMIN") {
      // 🚨 prevent invalid API call
      if (!projectId) return;

      res = await API.get(`/tasks/project/${projectId}`);
    } else {
      res = await API.get(`/tasks/user/${userId}`);
    }

    setTasks(res.data || []);
  } catch (err) {
    console.log("error loading tasks", err);
  }
};
  //  LOAD USERS (ADMIN)
  const loadUsers = async () => {
    try {
      const res = await API.get("/users");
      setUsers(res.data);
    } catch {
      console.log("error loading users");
    }
  };

  //  CREATE TASK
  const addTask = async () => {
  if (role !== "ADMIN") {
    alert("Only admin can create task");
    return;
  }

  if (!projectId) {
    alert("Project not selected");
    return;
  }

  if (!title || !assignedUser) {
    alert("Please fill all fields");
    return;
  }

  try {
    await API.post("/tasks", {
      title,
      projectId,
      assignedToId: assignedUser,
    });

    setTitle("");
    setAssignedUser("");
    loadTasks();
  } catch (err) {
    console.log(err);
    alert("Error creating task");
  }
};

  // UPDATE STATUS
  const updateStatus = async (id, status) => {
    try {
      await API.put(
        `/tasks/${id}/status?status=${status}&userId=${userId}`
      );
      loadTasks();
    } catch {
      console.log("error updating status");
    }
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">
        Tasks
      </h2>

      {/* ADMIN ONLY */}
      {role === "ADMIN" && (
        <div className="bg-white shadow-md rounded-xl p-4 mb-6 border">
          <h3 className="text-lg font-semibold mb-3">
            Create Task
          </h3>

          <div className="flex gap-3 flex-wrap">
            <input
              placeholder="Enter task title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              className="flex-1 border p-2 rounded-lg"
            />

            <select
              value={assignedUser}
              onChange={(e) => setAssignedUser(e.target.value)}
              className="border p-2 rounded-lg"
            >
              <option value="">Assign user</option>

              {users.map((u) => (
                <option key={u.id} value={u.id}>
                  {u.name}
                </option>
              ))}
            </select>

            <button
              onClick={addTask}
              className="bg-green-600 text-white px-4 rounded-lg"
            >
              Add Task
            </button>
          </div>
        </div>
      )}

      {/* TASK LIST */}
      <div className="space-y-4">
        {tasks.length === 0 && <p>No tasks found</p>}

        {tasks.map((t) => (
          <div
            key={t.id}
            className="bg-white shadow-md rounded-xl p-4 border"
          >
            <div className="flex justify-between items-center">
              <div>
                <p className="font-semibold">{t.title}</p>
                <p className="text-sm text-gray-500">
                  Assigned to: {t.assignedToName || "N/A"}
                </p>
              </div>

              {/* ONLY ASSIGNED USER CAN UPDATE */}
              {Number(t.assignedToId) === userId ? (
                <select
                  value={t.status}
                  onChange={(e) =>
                    updateStatus(t.id, e.target.value)
                  }
                  className="border p-1 rounded"
                >
                  <option>TODO</option>
                  <option>IN_PROGRESS</option>
                  <option>DONE</option>
                </select>
              ) : (
                <span className="text-sm">{t.status}</span>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}