import logo from './logo.svg';
import './App.css';
import NavScrollExample from "./views/menu/NavScrollExample";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./views/home/Home";
import Login from "./views/login/Login";
import SignUp from "./views/signup/SignUp";
import {AuthProvider} from "./context";
import PrivateProfilePage from "./views/privateuser/PrivateProfilePage";
import {user} from "./context/Reducer";
import CompanyPage from './views/company/CompanyPage';
import JobPage from './views/job/JobPage';
import ConnectionRequests from "./views/connections/ConnectionRequests";

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <div className="App">
          <BrowserRouter>
            <NavScrollExample/>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/login" element={<Login/>}/>
              <Route path="/signup" element={<SignUp/>}/>
              <Route path="/connections" element={<ConnectionRequests/>}/>
              <Route path="/mypage" element={<PrivateProfilePage currentProfile={user}/>}/>
              <Route path="/mycompany" element={<CompanyPage currentProfile={user}/>}/>
              <Route path="/profile/:id" element={<PrivateProfilePage />}/>
              <Route path="/company/:id" element={<CompanyPage />}/>
              <Route path="/job/:id" element={<JobPage />}/>
            </Routes>
          </BrowserRouter>
        </div>
      </AuthProvider>
    </div>
  );
}

export default App;
