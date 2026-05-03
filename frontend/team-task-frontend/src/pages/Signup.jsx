import { useState } from "react";
import API from "../api/axios";
import { useNavigate } from "react-router-dom";

export default function Signup() {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [role, setRole] = useState("MEMBER");

  const nav = useNavigate();

  // get current logged user role
  const currentRole = localStorage.getItem("role")?.replace("ROLE_", "");

  const handleSignup = async () => {
  try {
    await API.post("/auth/register", data);

    alert("account created");

    if (currentRole === "ADMIN") {
      setData({ name: "", email: "", password: "" });
    } else {
      nav("/");
    }

  } catch (err) {
    console.error(err);
    alert(err.response?.data || "error while signup");
  }
};

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-950 via-blue-800 to-blue-600 flex flex-col items-center justify-center">

      {/* heading */}
      <div className="text-center mb-8">
        <h1 className="text-4xl font-bold text-white mb-2">
          Task Manager
        </h1>

        <p className="text-gray-200 text-lg">
          Create your account
        </p>

        <p className="text-gray-300 text-sm mt-1">
          Join and start managing your team efficiently
        </p>
      </div>

      {/* signup card */}
      <div className="bg-white shadow-xl rounded-xl p-6 w-80">

        <h2 className="text-xl font-semibold text-center mb-4 text-gray-800">
          Signup
        </h2>

        <input
          type="text"
          placeholder="Enter name"
          value={data.name}
          className="border p-2 w-full mb-3 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          onChange={(e) =>
            setData({ ...data, name: e.target.value })
          }
        />

        <input
          type="email"
          placeholder="Enter email"
          value={data.email}
          className="border p-2 w-full mb-3 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          onChange={(e) =>
            setData({ ...data, email: e.target.value })
          }
        />

        <input
          type="password"
          placeholder="Enter password"
          value={data.password}
          className="border p-2 w-full mb-3 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          onChange={(e) =>
            setData({ ...data, password: e.target.value })
          }
        />

        {/* ONLY ADMIN CAN SEE ROLE OPTION */}
        {currentRole === "ADMIN" && (
          <select
            className="border p-2 w-full mb-3 rounded"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="MEMBER">Member</option>
            <option value="ADMIN">Admin</option>
          </select>
        )}

        <button
          className="bg-blue-600 hover:bg-blue-700 transition text-white p-2 w-full rounded font-medium"
          onClick={handleSignup}
        >
          Signup
        </button>

        {/* show login link only if not admin */}
        {currentRole !== "ADMIN" && (
          <p
            className="mt-4 text-center text-blue-600 cursor-pointer hover:underline"
            onClick={() => nav("/")}
          >
            Already have an account? Login
          </p>
        )}

      </div>

    </div>
  );
}