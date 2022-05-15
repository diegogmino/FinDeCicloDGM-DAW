import './App.css';
import MainPage from './pages/mainPage/MainPage';
import AboutUs from './pages/aboutUs/AboutUs';

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
        <Route path="/quienes-somos" element={<AboutUs/>}/>
      </Routes>
    </Router>  
    </div>
  );
}

export default App;
