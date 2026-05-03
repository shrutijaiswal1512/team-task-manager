import { useState } from "react";
import API from "../api/axios";

export default function AddUser() {
  const [data, setData] = useState({
    name: "",
    email: "",
    password: "",
    role: "MEMBER",
  });

  const handleCreate = async () => {
    try {
      await API.post("/users", data);
      alert("user created");
      setData({ name: "", email: "", password: "", role: "MEMBER" });
    } catch (err) {
      alert("error creating user");
    }
  };

  return (
    <div className="p-5">
      <h2 className="text-xl mb-4 font-bold">Add User</h2>

      <input
        placeholder="name"
        className="border p-2 mb-2 block"
        value={data.name}
        onChange={(e) => setData({ ...data, name: e.target.value })}
      />

      <input
        placeholder="email"
        className="border p-2 mb-2 block"
        value={data.email}
        onChange={(e) => setData({ ...data, email: e.target.value })}
      />

      <input
        type="password"
        placeholder="password"
        className="border p-2 mb-2 block"
        value={data.password}
        onChange={(e) => setData({ ...data, password: e.target.value })}
      />

      <select
        className="border p-2 mb-2"
        value={data.role}
        onChange={(e) => setData({ ...data, role: e.target.value })}
      >
        <option value="MEMBER">Member</option>
        <option value="ADMIN">Admin</option>
      </select>

      <button
        className="bg-blue-600 text-white px-4 py-2"
        onClick={handleCreate}
      >
        Create User
      </button>
    </div>
  );
}