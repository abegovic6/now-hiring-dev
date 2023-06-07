import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import Container from "react-bootstrap/Container";
import React from "react";
import {SUBMENU} from "./util";
import './../../App.css'

export default function ListNavbar(props) {
    const {selected, setSelected} = props;


    return (
        <Navbar expand="lg" className="lighterColorBackground sticky-top possitionOfSecondNavbar" variant="dark">
            <Container className="justify-content-center">
                <Nav style={{width: '100%'}}>
                    <Nav.Link onClick={() => setSelected(SUBMENU.COMPANIES)} style={{width: '33%'}} active={selected === SUBMENU.COMPANIES}>{SUBMENU.COMPANIES}</Nav.Link>
                    <Nav.Link onClick={() => setSelected(SUBMENU.PROFILES)} style={{width: '33%'}} active={selected === SUBMENU.PROFILES}>{SUBMENU.PROFILES}</Nav.Link>
                    <Nav.Link onClick={() => setSelected(SUBMENU.JOBS)} style={{width: '33%'}} active={selected === SUBMENU.JOBS}>{SUBMENU.JOBS}</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    );
}