import './App.css';
import MainPage from './pages/mainPage/MainPage';
import AboutUs from './pages/aboutUs/AboutUs';
import ContactPage from './pages/contact/ContactPage';
import Login from './components/login/Login';

import {
  BrowserRouter as Router,
  Route,
  Routes,
} from 'react-router-dom';


function App() {

  return (
    <div>
    <Router>
      <Routes>
        <Route path="/" element={<MainPage/>}/>
        <Route path="/index" element={<MainPage/>}/>
        <Route path="/login" element={<Login/>}/>
        <Route path="/quienes-somos" element={<AboutUs/>}/>
        <Route path="/contacto" element={<ContactPage/>}/>
      </Routes>
    </Router>  
    </div>
  );
}

export default App;
