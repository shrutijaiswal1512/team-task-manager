import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import Login from './pages/Login'
import Navbar from './components/Navbar'
import Dashboard from './pages/Dashboard'
import Signup from './pages/Signup'
import Projects from './pages/Projects'
import Tasks from './pages/Tasks'
import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AddUser from './pages/AddUser'

function App() {
  const [count, setCount] = useState(0)

  return (
   
      
<BrowserRouter>
      {/* navbar common for all pages */}
      <Navbar />

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/projects" element={<Projects />} />
        <Route path="/tasks/:projectId" element={<Tasks />} />
        <Route path="/add-user" element={<AddUser />} />
        <Route path="/my-tasks" element={<Tasks />} />
      </Routes>
    </BrowserRouter>
  
      
 
  );
}

export default App
