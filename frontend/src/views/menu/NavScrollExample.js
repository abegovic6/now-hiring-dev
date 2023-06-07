import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {logout, useAuthDispatch} from "../../context";
import {useNavigate} from "react-router-dom";
import {token, user} from "../../context/Reducer";
import mainLogo from'../../icons/logo.png';
import bellIcon from'../../icons/bell.svg';
import connectionsIcon from'../../icons/people.svg';
import React, {useEffect, useState} from "react";
import '../../App.css';
import {NOTIFICATION_TYPES, PROFILE} from "../utils";
import {get, put} from "../../methods";

function NavScrollExample(props) {
    const dispatch = useAuthDispatch() // read dispatch method from context
    const navigate = useNavigate();

    const [notifications, setNotifications] = useState([])
    const [connectionCount, setConnectionCount] = useState([])

    useEffect(() => {
        if (user) {
            let interval = setInterval(() => {
                get('http://localhost:3000/user-service/notification/' + user.uuid + '/all', undefined, token )
                    .then(response => {
                        setNotifications(response)
                        setConnectionCount(response.filter(r => r.type === NOTIFICATION_TYPES.CONNECTION).length)
                    })
            }, 10000);

            return () => {
                clearInterval(interval);
            };
        }

    }, [user])

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

    const clearNotifications = () => {
        put('http://localhost:3000/user-service/notification/' + user.uuid + '/all', undefined, undefined, token)
            .then(response => {
                setNotifications(response)
            })
    }

    const connectionRequests = () => {
        return <Nav.Link href="/connections"><div>
            <img src={connectionsIcon} height={20} alt="notificaation" />
            <span className="badge bg-danger badge-dot" >{connectionCount}</span>
        </div></Nav.Link>
    }

    const getNotifications = () => {
        return <NavDropdown className="possitionOfNavbar" title={
            <div>
                <img src={bellIcon} height={20} alt="notificaation" />
                <span className="badge bg-danger badge-dot">{notifications.length}</span>
            </div>
        } >
            <NavDropdown.Item onClick={clearNotifications}>Clear notifications</NavDropdown.Item>
            <NavDropdown.Divider />
            {
                notifications.map(response => {
                    return <NavDropdown.Item href={
                        response.type === NOTIFICATION_TYPES.JOB_APPLICATION ?
                            '/profile/' + response.fromUser.uuid :
                        response.type === NOTIFICATION_TYPES.JOB_CREATED ?
                            '/company/' + response.fromUser.uuid :
                            (response.type === NOTIFICATION_TYPES.REVIEW || response.type === NOTIFICATION_TYPES.RECOMMENDATION) ?
                            (user.userType === PROFILE.PRIVATE ? '/mypage' : '/mycompany') :
                        '/connections'
                    }>{response.text}</NavDropdown.Item>
                })
            }

        </NavDropdown>
    }

    const dropdown = () => {
        return <NavDropdown className="possitionOfNavbar" title={"Account"} >
            {
                user.userType === PROFILE.PRIVATE && myPage()
            }

            {
                user.userType === PROFILE.COMPANY && myCompanyPage()
            }
            <NavDropdown.Divider />
            <NavDropdown.Item onClick={handleLogout}>Log out</NavDropdown.Item>
        </NavDropdown>
    }



    const myPage = () => {
        return <NavDropdown.Item href="/mypage">Profile</NavDropdown.Item>
    }

    const myCompanyPage = () => {
        return <NavDropdown.Item href="/mycompany">Company</NavDropdown.Item>
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
                                    connectionRequests()
                                }
                                {getNotifications()}
                                {
                                    dropdown()
                                }
                            </>
                        }
                    </Nav>
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