import { Link } from 'react-router-dom';
import './returnButtonLogo.css';

import logo from '../../img/dcine-logo.png'

export default function ReturnButtonLogo() {
    return (

        <Link to="/index" className="imageSize">
            <img className="roundBorder" src={logo}/>
        </Link>

    );
}