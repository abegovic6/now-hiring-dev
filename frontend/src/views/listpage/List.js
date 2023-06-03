import ListCard from "./ListCard";
import React, {useEffect, useState} from "react";
import ListNavbar from "./ListNavbar";
import {SUBMENU} from "./util";
import profilePlaceholder from '../../icons/profileplaceholder.png';
import companyPlaceHolder from '../../icons/companyplaceholder.jpg';
import jobPlaceholder from '../../icons/jobplaceholder.png';
import {get} from "../../methods";
import {token} from "../../context/Reducer";
import {PROFILE} from "../utils";


export default function List(params) {
    const [selected, setSelected] = useState(SUBMENU.COMPANIES);
    const [companies, setCompanies] = useState([]);
    const [profiles, setProfiles] = useState([]);
    const [jobs, setJobs] = useState([]);

    useEffect(() => {
        get('user-service/user/all', undefined, token)
            .then(users => {
                console.info(users);
                let companiesCopy = [];
                let privateCopy = []
                for (let us of users) {
                    if (us.userType === PROFILE.COMPANY) {
                        companiesCopy.push(us);
                    } else {
                        privateCopy.push(us);
                    }
                }
                setCompanies(companiesCopy);
                setProfiles(privateCopy);

                return get('job-service/job/all', undefined, token)
            }).then(jobs => {
                console.info(jobs);
                setJobs(jobs);
        })
    }, [])


    return <>
        <ListNavbar selected={selected} setSelected={setSelected}/>
        {
            selected === SUBMENU.COMPANIES &&
            companies.map(card => {
                return <ListCard
                    key = {card.id}
                    image={companyPlaceHolder}
                    title={card.displayValue}
                    location={card.location}
                    description={card.description}
                />
            })
        }

        {
            selected === SUBMENU.PROFILES &&
            profiles.map(card => {
                return <ListCard
                    key = {card.id}
                    image={profilePlaceholder}
                    title={card.displayValue}
                    location={card.location}
                    description={card.description}
                />
            })
        }

        {
            selected === SUBMENU.JOBS &&
            jobs.map(card => {
                return <ListCard
                    key = {card.id}
                    image={jobPlaceholder}
                    title={card.title}
                    location={card.location}
                    description={card.description}

                />
            })
        }

    </>
}