import React, {useEffect, useState} from 'react';
import {
    MDBCol,
    MDBContainer,
    MDBRow,
    MDBCard,
    MDBCardText,
    MDBCardBody,
    MDBCardImage,
    MDBBtn,
    MDBListGroup,
    MDBListGroupItem,
    MDBIcon, MDBInput
} from 'mdb-react-ui-kit';
import profilePlaceholder from '../../icons/profileplaceholder.png';
import {token, user} from "../../context/Reducer";
import {get, post} from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";
import StarRatings from "react-star-ratings/build/star-ratings";
import plusSign from '../../icons/plus.svg';
import editSign from '../../icons/pencil.svg';
import {Modal} from "react-bootstrap";
import {Button} from "reactstrap";
import EditProfileModal from "./EditProfileModal";
import AddJobModal from "./AddJobModal";
import {useNavigate, useParams} from "react-router-dom";
//import AddExperienceModal from "./AddExperienceModal";

import jobPlaceholder from '../../icons/jobplaceholder.png';
import ListCard from "../listpage/ListCard";
import JobCard from './JobCard';


export default function CompanyPage(props) {

    const {currentProfile} = props;
    const [reviews, setReviews] = useState([])
    const [isLoading, setIsLoading] = useState(true);
    const [editProfile, setEditProfile] = useState(false);
    const [addJob, setAddJob] = useState(false);
    const [editJob, setEditJob] = useState(false);
    const [profile, setProfile] = useState();
    const [isMy, setIsMy] = useState(false);
    const params = useParams();
    const [connections, setConnections] = useState([])


    const [jobs, setJobs] = useState([])


    useEffect(() => {
        if (currentProfile) {
            setIsLoading(true);
            get('user-service/user/uuid/' + currentProfile.uuid, undefined, token)
                .then(response => {

                    setProfile(response);
                    setIsMy(response.uuid === user.uuid)
                    return get('job-service/job/getcompanyjobs/' + currentProfile.uuid, undefined, token)
                }).then(response => {
                setJobs(response);
                return get('http://localhost:3000/user-service/connection/' + params.id + '/all', undefined, token)

            }).then(response => {
                setConnections(response)
                setIsLoading(false);
            })
        } else {
            if (params && params.id) {
                setIsLoading(true);
                get('http://localhost:3000/user-service/user/uuid/' + params.id, undefined, token)
                    .then(response => {
                        setProfile(response);
                        setIsMy(response.uuid === user.uuid)
                        return get('http://localhost:3000/job-service/job/getcompanyjobs/' + response.uuid, undefined, token)
                    }).then(response => {
                    setJobs(response);
                    return get('http://localhost:3000/user-service/connection/' + params.id + '/all', undefined, token)

                }).then(response => {
                    setConnections(response)
                    setIsLoading(false);
                })
            }
        }


    }, [])

    const onEditProfileClose = () => {
        setEditProfile(false);
        window.location.reload();
    }
    const onAddJobClose = () => {
        setAddJob(false);
        window.location.reload();
    }


    function isConnected() {
        return connections.find(connection => connection.uuid === user.uuid)
    }

    function connect() {
        setIsLoading(true)
        post('http://localhost:3000/user-service/connection/' + profile.uuid + '/start/' + user.uuid, undefined, undefined, token)
            .then(response => {
                if (response.errors) {
                    alert(response.errors[0])
                } else {
                    alert('Connection request successfully sent!')
                }
                setIsLoading(false);
            })
    }

    return (<>
            {isLoading ? <LoadingSpinner/> :
                <section style={{backgroundColor: '#eee'}}>
                    <MDBContainer className="py-5">
                        <MDBRow>
                            <MDBCol lg="4">
                                <MDBCard className="mb-4">
                                    <MDBCardBody className="text-center">
                                        <MDBCardImage
                                            src={profilePlaceholder}
                                            alt="avatar"
                                            className="rounded-circle"
                                            style={{width: '150px'}}
                                            fluid/>
                                        <p className="text-muted mb-1">{profile.displayValue}</p>
                                        <p className="text-muted mb-1">{profile.locationDTO.displayValue}</p>
                                        <p className="text-muted mb-1">{"Connections: " + profile.connections.length}</p>
                                        {
                                            !isMy && <div className="d-flex justify-content-center mb-2">
                                                {
                                                    !isConnected() &&
                                                    <MDBBtn className="subColorBackground mainColorText" onClick={connect}>Connect</MDBBtn>
                                                }

                                                <MDBBtn outline className="mainColorBackground blackText">Recommend</MDBBtn>
                                            </div>
                                        }

                                    </MDBCardBody>
                                </MDBCard>


                            </MDBCol>


                            <MDBCol lg="8">
                                <MDBCard className="mb-4">
                                    <MDBCardBody>
                                        {isMy &&
                                            <MDBRow>

                                                <MDBCol>
                                                    <div className='containerButtonText'>
                                                        {
                                                            isMy && <MDBCardImage src={editSign} alt='...' height={20}
                                                                                  onClick={() => setEditProfile(true)}/>
                                                        }
                                                    </div>
                                                </MDBCol>
                                            </MDBRow>
                                        }
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Company Name</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">{profile.displayValue}</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr/>
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Email</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">{profile.email}</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr/>
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Location</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText
                                                    className="text-muted">{profile.locationDTO.displayValue} </MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                        <hr/>
                                        <MDBRow>
                                            <MDBCol sm="3">
                                                <MDBCardText>Description</MDBCardText>
                                            </MDBCol>
                                            <MDBCol sm="9">
                                                <MDBCardText className="text-muted">{profile.description}</MDBCardText>
                                            </MDBCol>
                                        </MDBRow>
                                    </MDBCardBody>
                                </MDBCard>

                                <MDBCard className="mb-4">
                                    <MDBCardBody>
                                        <div className='containerButtonText'>
                                            {
                                                isMy && <MDBCardImage src={plusSign} alt='...' height={30}
                                                                      onClick={() => setAddJob(true)}/>
                                            }
                                            <MDBCardText style={{fontSize: '18px', fontWeight: "bold"}}
                                                         className="mb-4">Jobs</MDBCardText>
                                        </div>

                                        {
                                            jobs && jobs.map(card => {
                                                return <JobCard
                                                    key={card.id}
                                                    image={jobPlaceholder}
                                                    title={card.title}
                                                    location={card.location}
                                                    description={card.description}
                                                    jobId = {card.id}
                                                    expired = {card.expired}
                                                />
                                            })
                                        }
                                    </MDBCardBody>
                                </MDBCard>
                                <MDBCard className="mb-4">
                                    <MDBCardBody>
                                        <div className='containerButtonText'>
                                            {
                                                !isMy && <MDBCardImage src={plusSign} alt='...' height={30}/>
                                            }
                                            <MDBCardText style={{fontSize: '18px', fontWeight: "bold"}}
                                                         className="mb-4">Reviews</MDBCardText>
                                        </div>

                                        {
                                            reviews && reviews.map((review, index) => {
                                                return <MDBCol className="mb-4 mb-md-0">
                                                    <MDBCardText className="mb-1"
                                                                 style={{fontSize: '.9rem'}}>{review.creator.name}</MDBCardText>
                                                    <StarRatings
                                                        rating={review.number}
                                                        starDimension="10px"
                                                        starSpacing="5px"
                                                    />
                                                    <MDBCardText className="mb-4"
                                                                 style={{fontSize: '.7rem'}}>{review.comment}</MDBCardText>
                                                </MDBCol>


                                            })

                                        }
                                    </MDBCardBody>
                                </MDBCard>

                            </MDBCol>
                        </MDBRow>


                    </MDBContainer>
                </section>}

            {
                isMy && editProfile && <EditProfileModal open={editProfile} onClose={onEditProfileClose}/>
            }
            {
                isMy && addJob && <AddJobModal open={addJob} onClose={onAddJobClose}/>
            }
        </>

    );
}
