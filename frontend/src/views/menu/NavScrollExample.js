import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {logout, useAuthDispatch} from "../../context";
import {useNavigate} from "react-router-dom";
import {user} from "../../context/Reducer";
import mainLogo from'../../icons/logo.png';
import React from "react";
import '../../App.css';
import {PROFILE} from "../utils";
import  {useEffect, useState} from 'react';

function NavScrollExample(props) {
    const dispatch = useAuthDispatch() // read dispatch method from context
    const navigate = useNavigate();

    const handleLogout = () => {
        logout(dispatch).then(() => {
            navigate('/');
            window.location.reload();
        })
    }
    const getMenuLogo = () => {
        return <div className="logo">
            <div>
                <img src={mainLogo} height={50} alt="fireSpot" />
            </div>

        </div>
    }

    const logInMenuItem = () => {
        return <Nav.Link href="/login">Log in</Nav.Link>
    }

    const signUpMenuItem = () => {
        return <Nav.Link href="/signup">Sign Up</Nav.Link>
    }

    const logOutMenuItem = () => {
        return <Nav.Link onClick={handleLogout}>Log out</Nav.Link>
    }

    const myPage = () => {
        return <Nav.Link href="/mypage">Profile</Nav.Link>
    }

    const myCompanyPage = () => {
        return <Nav.Link href="/mycompany">Company</Nav.Link>
    }

    const [hideElements, setHideElements] = useState(false);

    useEffect(() => {
        const handleHideNavBarElementsUpdated = () => {
          const hideNavBarElements = localStorage.getItem('hideNavBarElements');
          if (hideNavBarElements === 'true') {
            setHideElements(true);
          }
        };
    
        // Add an event listener to handle the local storage update event
        document.addEventListener('hideNavBarElementsUpdated', handleHideNavBarElementsUpdated);
    
        // Clean up the event listener when the component unmounts
        return () => {
          document.removeEventListener('hideNavBarElementsUpdated', handleHideNavBarElementsUpdated);
        };
      }, []);


    return (
        <Navbar  expand="lg" className="darkColorBackground sticky-top" variant="dark">
            <Container>
                <Navbar.Brand href="/">{getMenuLogo()}</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav">
                    {!hideElements && <><Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav><Nav>
                            {!user &&
                                <>
                                    {logInMenuItem()}
                                    {signUpMenuItem()}
                                </>}
                            {user &&
                                <>
                                    {user.userType === PROFILE.PRIVATE && myPage()}

                                    {user.userType === PROFILE.COMPANY && myCompanyPage()}

                                    {logOutMenuItem()}
                                </>}
                        </Nav></>
}
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavScrollExample;