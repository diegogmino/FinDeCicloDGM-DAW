import './App.css';
import MainPage from './pages/mainPage/MainPage';

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
      </Routes>
    </Router>  
    </div>
  );
}

export default App;
