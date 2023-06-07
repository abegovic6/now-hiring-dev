import ConnectionRequestCard from "./ConnectionRequestCard";
import {token, user} from "../../context/Reducer";
import profilePlaceholder from '../../icons/profileplaceholder.png';
import companyPlaceHolder from '../../icons/companyplaceholder.jpg';
import React, {useEffect, useState} from "react";
import LoadingSpinner from "../loading/LoadingSpinner";
import {get, put} from "../../methods";
import {PROFILE} from "../utils";
import ConnectionsNavbar from "./ConnectionsNavbar";
import {SUBMENU} from "./util";
import ListCard from "../listpage/ListCard";
import {useNavigate} from "react-router-dom";

export default function ConnectionRequests() {
    const [connectionRequests, setConnectionRequests] = useState([])
    const [connections, setConnections] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [selected, setSelected] = useState(SUBMENU.CONNECTIONS);
    const navigate = useNavigate();

    useEffect(() => {
        if (user) {
            get('http://localhost:3000/user-service/connection/' + user.uuid + '/all/requests', undefined, token)
                .then(response => {
                    setConnectionRequests(response);

                    return get('http://localhost:3000/user-service/connection/' + user.uuid + '/all', undefined, token)


                }).then(response => {
                    setConnections(response)
                setIsLoading(false)
            })
        }

    }, [user])

    const onAccept = (connection) => {
        setIsLoading(true)
        put('user-service/connection/' + user.uuid +  '/accept/' + connection.uuid, undefined, undefined, token)
            .then(response => {
                if (response.error) {
                    alert(response.error[0])
                } else {
                    alert(response.message)
                }
                return get('user-service/connection/' + user.uuid + '/all/requests', undefined, token)

            }).then(response => {
            setConnectionRequests(response);
            setIsLoading(false)
        })
    }

    const onReject = (connection) => {
        setIsLoading(true)
        put('user-service/connection/' + user.uuid +  '/reject/' + connection.uuid, undefined, undefined, token)
            .then(response => {
                if (response.error) {
                    alert(response.error[0])
                } else {
                    alert(response.message)
                }
                return get('user-service/connection/' + user.uuid + '/all/requests', undefined, token)

            }).then(response => {
            setConnectionRequests(response);
            setIsLoading(false)
        })
    }

    return <>
        {
            isLoading ? <LoadingSpinner/> : <>
                <ConnectionsNavbar selected={selected} setSelected={setSelected} requests={connectionRequests} />
                {
                    selected === SUBMENU.CONNECTIONS &&
                    connections.map((card, index) => {
                    return <ListCard
                        key = {card.id}
                        image={card.userType === PROFILE.PRIVATE ? profilePlaceholder : companyPlaceHolder}
                        title={card.displayValue}
                        location={card.location}
                        description={card.description}
                        onArrowClick={() => {
                            if (card.userType === PROFILE.PRIVATE) {
                                navigate('/profile/' + card.uuid)
                            } else {
                                navigate('/company/' + card.uuid)
                            }

                        }
                        }
                    />
                })
                }

                {
                    selected === SUBMENU.REQUESTS &&
                    connectionRequests.map((connection, index) => {
                        return <ConnectionRequestCard
                            key={index}
                            creationUser={connection}
                            image={connection.userType === PROFILE.PRIVATE ? profilePlaceholder : companyPlaceHolder}
                            onAccept={() => onAccept(connection)}
                            onReject={() => onReject(connection)}
                        />
                    })
                }

            </>

        }

    </>
}