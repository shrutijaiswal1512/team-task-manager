import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const nav = useNavigate();

  // getting role properly
  const role = localStorage.getItem("role")?.replace("ROLE_", "");

  const logout = () => {
    localStorage.clear();
    nav("/");
  };

  return (
    <div className="bg-gray-900 text-white px-6 py-3 flex justify-between items-center shadow-md">

      {/* logo */}
      <h1 className="text-lg font-bold cursor-pointer" onClick={() => nav("/dashboard")}>
        Task Manager
      </h1>

      <div className="flex gap-6 items-center">

        <button
          className="hover:text-blue-400 transition"
          onClick={() => nav("/dashboard")}
        >
          Dashboard
        </button>
        {role === "MEMBER" && (
  <button onClick={() => nav("/my-tasks")}>
    My Tasks
  </button>
)}

        <button
          className="hover:text-blue-400 transition"
          onClick={() => nav("/projects")}
        >
          Projects
        </button>

        
        {/*<button
          className="hover:text-blue-400 transition"
          onClick={() => nav("/tasks")}
        >
          Tasks
        </button>
*/}
        {/*  ADMIN ONLY */}
        {role === "ADMIN" && (
          <button
            className="bg-blue-600 hover:bg-blue-700 px-3 py-1 rounded transition"
            onClick={() => nav("/add-user")}
          >
            Add User
          </button>
        )}

        <button
          className="bg-red-500 hover:bg-red-600 px-3 py-1 rounded transition"
          onClick={logout}
        >
          Logout
        </button>

      </div>
    </div>
  );
}