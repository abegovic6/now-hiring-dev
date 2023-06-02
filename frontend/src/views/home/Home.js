import React from 'react';
import { Grid, Header, Image, Segment} from "semantic-ui-react";
import {MDBBtn} from "mdb-react-ui-kit";

import {user} from "../../context/Reducer";
import NotLoggedInHomePage from "./NotLoggedInHomePage";
import List from "../listpage/List";

export default function Home() {
    return <>
        {
            !user && <NotLoggedInHomePage />
        }
        {
            user && <List/>
        }
    </>
}