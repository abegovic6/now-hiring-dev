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

    const myTicketsMenuItem = () => {
        return <Nav.Link href="/mytickets">My tickets</Nav.Link>
    }

    const myTasksMenuItem = () => {
        return <Nav.Link href="/mytasks">My tasks</Nav.Link>
    }

    const eventMenuItem = () => {
        return <Nav.Link href="/event">Event</Nav.Link>
    }

    const newEventMenuItem = () => {
        return <Nav.Link href="/new-event">Create Event</Nav.Link>
    }

    const myProfile = () => {
        return <Nav.Link  href="/my-profile" >Profile</Nav.Link>
    }



    const logOutMenuItem = () => {
        return <Nav.Link onClick={handleLogout}>Log out</Nav.Link>
    }


    return (
        <Navbar  expand="lg" className="darkColorBackground sticky-top" variant="dark">
            <Container>
                <Navbar.Brand href="#home">{getMenuLogo()}</Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                    </Nav>
                    <Nav>
                        {
                            !user &&
                            <>
                                {
                                    logInMenuItem()
                                }
                                {
                                    signUpMenuItem()
                                }
                            </>

                        }
                        {
                            user &&
                            <>
                                {
                                  user.userType === 'USER' &&
                                    <>
                                        {
                                            myTicketsMenuItem()
                                        }
                                    </>
                                }
                                {
                                    user.userType === 'ADMIN' &&
                                    <>
                                        {
                                            newEventMenuItem()
                                        }
                                        {
                                            myTasksMenuItem()
                                        }
                                    </>
                                }
                                {
                                    logOutMenuItem()
                                }
                            </>
                        }
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavScrollExample;