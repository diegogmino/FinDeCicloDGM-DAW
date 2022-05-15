import './App.css';
import MainPage from './pages/mainPage/MainPage';
import AboutUs from './pages/aboutUs/AboutUs';
import ContactPage from './pages/contact/ContactPage';
import Login from './pages/login/Login';
import Signup from './pages/singup/Signup';
import FilmsPage from './pages/filmsPage/FilmsPage';
import DetailPage from './pages/detailPage/DetailPage';

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
        <Route path="/signup" element={<Signup/>}/>
        <Route path="/quienes-somos" element={<AboutUs/>}/>
        <Route path="/contacto" element={<ContactPage/>}/>
        <Route path="/peliculas" element={<FilmsPage/>}/>
        <Route path="/detalle/:id" element={<DetailPage/>}/>
      </Routes>
    </Router>  
    </div>
  );
}

export default App;