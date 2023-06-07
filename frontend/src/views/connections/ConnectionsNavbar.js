import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import React from "react";

import './../../App.css'
import {SUBMENU} from "./util";

export default function ConnectionsNavbar(props) {
    const {selected, setSelected, requests} = props;


    return (
        <Navbar expand="lg" className="lighterColorBackground sticky-top possitionOfSecondNavbar" variant="dark">
            <Container className="justify-content-center">
                <Nav style={{width: '100%'}}>
                    <Nav.Link onClick={() => setSelected(SUBMENU.CONNECTIONS)} style={{width: '50%'}} active={selected === SUBMENU.CONNECTIONS}>{SUBMENU.CONNECTIONS}</Nav.Link>
                    <Nav.Link onClick={() => setSelected(SUBMENU.REQUESTS)} style={{width: '50%'}} active={selected === SUBMENU.REQUESTS}>{
                        <>{SUBMENU.REQUESTS}
                        <span className="badge bg-danger badge-dot" style={{marginLeft: '2px'}}>{requests.length}</span></>
                    }</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
}