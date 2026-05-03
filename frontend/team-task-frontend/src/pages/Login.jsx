import { useState } from "react";
import API from "../api/axios";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const nav = useNavigate();

  const handleLogin = async () => {
  try {
    const res = await API.post("/auth/login", {
      email,
      password,
    });

    // saving token
    localStorage.setItem("token", res.data.token);

    // saving role (important for UI)
    localStorage.setItem("role", res.data.role);

    localStorage.setItem("userId", res.data.userId);

    nav("/dashboard");
  } catch (err) {
    alert("invalid credentials");
  }
};

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-950 via-blue-800 to-blue-600 flex flex-col items-center justify-center">

      {/* heading section */}
      <div className="text-center mb-8">

        {/* logo */}
        <h1 className="text-4xl font-bold text-white mb-2">
           Task Manager
        </h1>

        <p className="text-gray-200 text-lg">
          Welcome to Task Management App
        </p>

        <p className="text-gray-300 text-sm mt-1">
          Manage projects, assign tasks and track progress easily
        </p>

      </div>

      {/* login card */}
      <div className="bg-white shadow-xl rounded-xl p-6 w-80">

        <h2 className="text-xl font-semibold text-center mb-4 text-gray-800">
          Login
        </h2>

        <input
          type="email"
          placeholder="Enter email"
          className="border p-2 w-full mb-3 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Enter password"
          className="border p-2 w-full mb-3 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button
          className="bg-blue-600 hover:bg-blue-700 transition text-white p-2 w-full rounded font-medium"
          onClick={handleLogin}
        >
          Login
        </button>

        <p
          className="mt-4 text-center text-blue-600 cursor-pointer hover:underline"
          onClick={() => nav("/signup")}
        >
          Don’t have an account? Signup
        </p>

      </div>

    </div>
  );
}