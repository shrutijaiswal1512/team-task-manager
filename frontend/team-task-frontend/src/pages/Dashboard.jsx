import { useEffect, useState } from "react";
import API from "../api/axios";

export default function Dashboard() {
  const [data, setData] = useState({});

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const res = await API.get("/dashboard");
      setData(res.data);
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="p-5">
      <h2 className="text-xl mb-4">Dashboard</h2>

      <div className="grid grid-cols-3 gap-4">
        <div className="bg-blue-200 p-4">
          total tasks: {data.totalTasks}
        </div>

        <div className="bg-green-200 p-4">
          completed: {data.completed}
        </div>

        <div className="bg-red-200 p-4">
          overdue: {data.overdue}
        </div>
      </div>
    </div>
  );
}