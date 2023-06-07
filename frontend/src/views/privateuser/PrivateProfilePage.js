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
import {get, post, put} from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";
import StarRatings from "react-star-ratings/build/star-ratings";
import plusSign from '../../icons/plus.svg';
import editSign from '../../icons/pencil.svg';
import {Modal} from "react-bootstrap";
import {Button} from "reactstrap";
import EditProfileModal from "./EditProfileModal";
import {useNavigate, useParams} from "react-router-dom";
import AddExperienceModal from "./AddExperienceModal";
import html2pdf from 'html2pdf.js';

export default function PrivateProfilePage(props) {
    const {currentProfile} = props;
    const [educations, setEducations] = useState([])
    const [experiences, setExperiences] = useState([])
    const [skills, setSkills] = useState([])
    const [reviews, setReviews] = useState([])
    const [addExperience, setAddExperience] = useState(false)
    const [isLoading, setIsLoading] = useState(true);
    const [editProfile, setEditProfile] = useState(false);
    const params = useParams();
    const [isMy, setIsMy] = useState(false);
    const [profile, setProfile] = useState();
    const [connections, setConnections] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        localStorage.setItem('hideNavBarElements', 'false');
        if (currentProfile) {
            setIsLoading(true);
            get('user-service/user/uuid/' + currentProfile.uuid, undefined, token)
                .then(response => {

                    setProfile(response);
                    setIsMy(response.uuid === user.uuid)
                    return get('feature-service/user/profile/' + currentProfile.uuid, undefined, token)
                }).then(response => {

                            setEducations(response.educations)
                            setExperiences(response.experiences)
                    setSkills(response.skills)



                return get('http://localhost:3000/user-service/connection/' + currentProfile.uuid + '/all', undefined, token)

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
                        return get('http://localhost:3000/feature-service/user/profile/' + params.id, undefined, token)
                    }).then(response => {

                    setEducations(response.educations)
                    setExperiences(response.experiences)
                    setSkills(response.skills)



                    return get('http://localhost:3000/recommendation-service/review/' + params.id + '/all', undefined,  token)
                }).then(response => {
                    setReviews(response);

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
    const onAddExperienceClose = () => {
        setAddExperience(false);
        //window.location.reload();
    }

    function getMonthName(monthNumber) {
        const date = new Date();
        date.setMonth(monthNumber - 1);

        return date.toLocaleString('en-US', { month: 'long' });
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

    const handleDownload = async (name) => {
        setDownload(true);
        localStorage.setItem('hideNavBarElements', 'true');
        document.dispatchEvent(new Event('hideNavBarElementsUpdated'));
        const element = document.body;
        const options = {
          filename: name + ' CV.pdf',
          html2canvas: { scale: 2 },
          jsPDF: { unit: 'in', format: 'letter', orientation: 'portrait' },
        };

        await html2pdf().set(options).from(element).save();
        localStorage.setItem('hideNavBarElements', 'false');
        window.location.reload();
      };

    return (<>
        {isLoading ? <LoadingSpinner /> :
        <section style={{ backgroundColor: '#eee' }}>
            <MDBContainer className="py-5">
                <MDBRow>
                    <MDBCol lg="4">
                        <MDBCard className="mb-4">
                            <MDBCardBody className="text-center">
                                <MDBCardImage
                                    src={profilePlaceholder}
                                    alt="avatar"
                                    className="rounded-circle"
                                    style={{ width: '150px' }}
                                    fluid />
                                <p className="text-muted mb-1">{profile.displayValue}</p>
                                <p className="text-muted mb-1">{profile.locationDTO.displayValue}</p>
                                <p className="text-muted mb-1">{"Connections: " + connections.length}</p>
                                {
                                    !isMy && <div className="d-flex justify-content-center mb-2">
                                        {
                                            !isConnected() &&
                                            <MDBBtn className="subColorBackground" onClick={connect}>Connect</MDBBtn>
                                        }

                                    !isMy && !download && <div className="d-flex justify-content-center mb-2">
                                        <MDBBtn className="subColorBackground">Connect</MDBBtn>
                                        <MDBBtn outline className="mainColorBackground">Recommend</MDBBtn>
                                        <Button onClick={() => handleDownload(profile.displayValue)}>Download CV</Button>
                                    </div>
                                }
                                {
                                    isMy && !download &&
                                    <Button onClick={() => handleDownload(profile.displayValue)}>Download CV</Button>
                                }

                            </MDBCardBody>
                        </MDBCard>


                        <MDBCard className="mb-4 mb-lg-0">
                            <MDBCardBody className="p-0">
                                <MDBListGroup flush className="rounded-3">
                                    {
                                        isMy && !download && <MDBListGroupItem >
                                            <div className='containerButtonText'>
                                                {
                                                    isMy && !download && <MDBCardImage src={plusSign} alt='...' height={30} />
                                                }
                                            </div>
                                        </MDBListGroupItem>
                                    }

                                    {
                                        skills && skills.map((skill, index) => {
                                            return <MDBListGroupItem className="d-flex justify-content-center align-items-center p-3">
                                                <MDBCardText>{skill.title}</MDBCardText>
                                            </MDBListGroupItem>
                                        })
                                    }
                                </MDBListGroup>
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
                                                isMy && !download && <MDBCardImage src={editSign} alt='...' height={20} onClick={() => setEditProfile(true)} />
                                            }
                                        </div>
                                    </MDBCol>
                                </MDBRow>
                                }
                                <MDBRow>
                                    <MDBCol sm="3">
                                        <MDBCardText>Full Name</MDBCardText>
                                    </MDBCol>
                                    <MDBCol sm="9">
                                        <MDBCardText className="text-muted">{profile.displayValue}</MDBCardText>
                                    </MDBCol>
                                </MDBRow>
                                <hr />
                                <MDBRow>
                                    <MDBCol sm="3">
                                        <MDBCardText>Email</MDBCardText>
                                    </MDBCol>
                                    <MDBCol sm="9">
                                        <MDBCardText className="text-muted">{profile.email}</MDBCardText>
                                    </MDBCol>
                                </MDBRow>
                                <hr />
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

                        <MDBRow className="mb-4">
                            <MDBCol md="6">
                                <MDBCard className="mb-4 mb-md-0">
                                    <MDBCardBody>
                                        <div className='containerButtonText'>
                                            {
                                                isMy && !download && <MDBCardImage src={plusSign} alt='...' height={30} />
                                            }
                                            <MDBCardText style={{ fontSize: '18px', fontWeight: "bold" }} className="mb-4">Education</MDBCardText>
                                        </div>


                                        {
                                            educations && educations.map((education, index) => {
                                                return <div key={index}>
                                                    <MDBCardText className="mb-1" style={{ fontSize: '.9rem' }}>{education.title}</MDBCardText>
                                                    <MDBCardText className="mb-1" style={{ fontSize: '.7rem' }}>{education.description}</MDBCardText>
                                                    {
                                                        education.endMonth && education.endYear ? <MDBCardText className="mb-4" style={{ fontSize: '.7rem' }}>
                                                            {getMonthName(education.startingMonth) + ' ' + education.startingYear + ' - ' + getMonthName(education.endMonth) + ' ' + education.endYear}
                                                        </MDBCardText> : <MDBCardText className="mb-4" style={{ fontSize: '.7rem' }}>
                                                            {getMonthName(education.startingMonth) + ' ' + education.startingYear + ' - PRESENT'}
                                                        </MDBCardText>
                                                    }


                                                </div>
                                            })
                                        }
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>

                            <MDBCol md="6">
                                <MDBCard className="mb-4 mb-md-0">
                                    <MDBCardBody>
                                        <div className='containerButtonText'>

                                            {
                                                isMy && !download && <MDBCardImage style={{ cursor: 'pointer' }} src={plusSign}
                                                                      alt='...'  height={30} onClick={() => setAddExperience(true)}/>
                                            }
                                            <MDBCardText style={{ fontSize: '18px', fontWeight: "bold" }} className="mb-4">Experience</MDBCardText>
                                        </div>
                                        {
                                            experiences && experiences.map((education, index) => {
                                                return <div key={index}>
                                                    <MDBCardText className="mb-1" style={{ fontSize: '.9rem' }}>{education.title}</MDBCardText>
                                                    <MDBCardText className="mb-1" style={{ fontSize: '.7rem' }}>{education.description}</MDBCardText>
                                                    {
                                                        education.endMonth && education.endYear ? <MDBCardText className="mb-4" style={{ fontSize: '.7rem' }}>
                                                            {getMonthName(education.startingMonth) + ' ' + education.startingYear + ' - ' + getMonthName(education.endMonth) + ' ' + education.endYear}
                                                        </MDBCardText> : <MDBCardText className="mb-4" style={{ fontSize: '.7rem' }}>
                                                            {getMonthName(education.startingMonth) + ' ' + education.startingYear + ' - PRESENT'}
                                                        </MDBCardText>
                                                    }


                                                </div>
                                            })
                                        }
                                    </MDBCardBody>
                                </MDBCard>
                            </MDBCol>
                        </MDBRow>

                        <MDBCard  md="6" >
                            <MDBCardBody>
                                <div className='containerButtonText'>
                                    {
                                        !isMy && !download && <MDBCardImage src={plusSign} alt='...' height={30} />
                                    }
                                    <MDBCardText style={{ fontSize: '18px', fontWeight: "bold" }} className="mb-4">Reviews</MDBCardText>
                                </div>

                                {
                                    reviews && reviews.map((review, index) => {
                                        return <MDBCol className="mb-4 mb-md-0">
                                            <MDBCardText className="mb-1" style={{ fontSize: '.9rem' }}>{review.creator.name}</MDBCardText>
                                            <StarRatings
                                                rating={review.number}
                                                starDimension="10px"
                                                starSpacing="5px"
                                            />
                                            <MDBCardText className="mb-4" style={{ fontSize: '.7rem' }}>{review.comment}</MDBCardText>
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
                addExperience && <AddExperienceModal open={addExperience} onClose={onAddExperienceClose}/>
            }

        {
            editProfile && <EditProfileModal open={editProfile} onClose={onEditProfileClose}/>
        }
    </>

    );
}