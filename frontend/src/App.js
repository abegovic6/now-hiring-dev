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
              <Route path="/mypage" element={<PrivateProfilePage profile={user}/>}/>
            </Routes>
          </BrowserRouter>
        </div>
      </AuthProvider>
    </div>
  );
}

export default App;
