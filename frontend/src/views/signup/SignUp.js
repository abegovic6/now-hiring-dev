import React, {useEffect, useState} from 'react'
import './../../App.css';
import {useNavigate} from "react-router-dom";
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBInput, MDBTextArea
}
    from 'mdb-react-ui-kit';
import justAPicture from '../../icons/imagee.png';
import {get, post, put} from "../../methods";
import {token, user} from "../../context/Reducer";
import LoadingSpinner from "../loading/LoadingSpinner";
import {PROFILE} from "../utils";

const SignUp = (props) => {
    const [email, setEmail] = useState('')
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [password, setPassword] = useState('');
    const [repeatedPassword, setRepeatedPassword] = useState('');
    const [description, setDescription] = useState('');
    const [companyName, setCompanyName] = useState('');
    const [codeSent, setCodeSent] = useState(false)
    const navigate = useNavigate();
    const [code, setCode] = useState('');
    const [userType, setUserType] = useState(undefined);
    const [isLoading, setIsLoading] = useState(true);
    const [locations, setLocations] = useState([]);
    const [location, setLocation] = useState();

    useEffect(() => {
        get('user-service/user/auth/locations')
            .then(response => {
                setLocations(response);
                setIsLoading(false);
            })
    }, [])

    const onSignUpPrivate = async () => {
        if (password !== repeatedPassword) {
            alert("Passwords need to match!")
        } else {
            let selectedLocation = undefined;

            for(let l of locations) {
                // OVDJE MORA == JER JE LOCATION STRING A TREBA ID
                if (l.id == location) {
                    selectedLocation = l;
                }
            }

            if (!email) {
                alert("Email is required field!")
            } else if (!password) {
                alert("Password is required field!")
            } else if (!firstName) {
                alert("First name is a required field!")
            } else if (!selectedLocation) {
                alert("Please specify a location!")
            } else {
                const newUser = {
                    email: email,
                    password: password,
                    firstName: firstName,
                    lastName: lastName,
                    description: description,
                    userType: PROFILE.PRIVATE,
                    locationDTO: selectedLocation,
                }
                setIsLoading(true);
                post('user-service/user/auth/register', newUser)
                    .then(response => {
                        if (response.error) {
                            alert(response.error[0])
                            setIsLoading(false);
                        } else {
                            setCodeSent(true);
                            setIsLoading(false);
                        }
                    })

            }


        }

    }

    const onSignUpCompany = async () => {
        if (password !== repeatedPassword) {
            alert("Passwords need to match!")
        } else {
            let selectedLocation = undefined;

            for(let l of locations) {
                // OVDJE MORA == JER JE LOCATION STRING A TREBA ID
                if (l.id == location) {
                    selectedLocation = l;
                }
            }

            if (!email) {
                alert("Email is required field!")
            } else if (!password) {
                alert("Password is required field!")
            } else if (!companyName) {
                alert("Company name is a required field!")
            } else if (!selectedLocation) {
                alert("Please specify a location!")
            } else {
                const newUser = {
                    email: email,
                    password: password,
                    companyName: companyName,
                    description: description,
                    userType: PROFILE.COMPANY,
                    locationDTO: selectedLocation,
                }
                setIsLoading(false);
                post('user-service/user/auth/register', newUser)
                    .then(response => {
                        if (response.error) {
                            alert(response.error[0])
                            setIsLoading(false);
                        } else {
                            setCodeSent(true)
                            setIsLoading(false);
                        }

                    })

            }


        }

    }

    const activateProfile = async () => {
        setIsLoading(true);
        const verify = {
            email: email,
            code: code
        }
        put('user-service/user/auth/verify', verify ).then(result => {
            if (!result.errors) {
                setIsLoading(false);
                navigate('/login');
                window.location.reload();


            } else {
                alert(result.errors[0])
                setIsLoading(false);
            }
        })

    }

    return (<>
        {
            isLoading  ? <LoadingSpinner /> : <MDBContainer fluid>
                <MDBRow>
                    <MDBCol sm='5' className='d-none d-sm-block px-0'>
                        <img src={justAPicture} className="w-100" style={{objectFit: 'cover', objectPosition: 'left'}}
                             alt="fireSpot"/>
                    </MDBCol>
                    <MDBCol sm='7'>
                        <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                            <p className="mb-4 h1 fw-bold subColorText ">SIGN UP</p>
                        </div>
                        {
                            !userType && <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                                <p className="mb-4 h5 subColorText ">What type of account do you want to create?</p>
                                <p></p>
                                <MDBBtn
                                    className="mb-4 px-5 mx-5 w-100 mainColorBackground subColorText"
                                    size='lg'
                                    onClick={() => setUserType('PRIVATE')}
                                >
                                    Create private account
                                </MDBBtn>
                                <MDBBtn
                                    className="mb-4 px-5 mx-5 w-100 mainColorBackground subColorText"
                                    size='lg'
                                    onClick={() => setUserType('COMPANY')}
                                >
                                    Create company account
                                </MDBBtn>
                            </div>
                        }

                        {
                            userType === 'PRIVATE' && !codeSent &&
                            <div className='d-flex flex-column justify-content-center h-custom-2 w-75 '>
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='First name'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setFirstName(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Last name'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setLastName(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Email address'
                                    id='formControlLg'
                                    type='email'
                                    size="lg"
                                    onChange={e => setEmail(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Password'
                                    id='formControlLg'
                                    type='password'
                                    size="lg"
                                    onChange={e => setPassword(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Repeated password'
                                    id='formControlLg'
                                    type='password'
                                    size="lg"
                                    onChange={e => setRepeatedPassword(e.target.value)}
                                />
                                <select className="mb-4 mx-5 w-100 form-select" aria-label="Default select example"
                                        onChange={event => {
                                            setLocation(event.target.value)
                                        }
                                        }>
                                    {
                                        locations && locations.map(l => {
                                            return <option value={l.id}>{l.displayValue}</option>
                                        })
                                    }
                                </select>
                                <MDBTextArea
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Description'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setDescription(e.target.value)}
                                />
                                <MDBBtn
                                    className="mb-4  mx-5 w-100 subColorBackground"
                                    size='lg'
                                    onClick={onSignUpPrivate}
                                >
                                    Sign up
                                </MDBBtn>
                                <p className='ms-5'>Don't have an account? <a href="/signup" className="link-info">Sign up
                                    here</a></p>
                            </div>
                        }
                        {
                            userType === 'COMPANY' && !codeSent &&
                            <div className='d-flex flex-column justify-content-center h-custom-2 w-75 '>
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Company name'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setCompanyName(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Email address'
                                    id='formControlLg'
                                    type='email'
                                    size="lg"
                                    onChange={e => setEmail(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Password'
                                    id='formControlLg'
                                    type='password'
                                    size="lg"
                                    onChange={e => setPassword(e.target.value)}
                                />
                                <MDBInput
                                    wrapperClass=' mx-5 w-100'
                                    label='Repeated password'
                                    id='formControlLg'
                                    type='password'
                                    size="lg"
                                    onChange={e => setRepeatedPassword(e.target.value)}
                                />
                                <select className="mb-4 mx-5 w-100 form-select" aria-label="Default select example"
                                        onChange={event => {
                                            setLocation(event.target.value)
                                        }
                                        }>
                                    {
                                        locations && locations.map(l => {
                                            return <option value={l.id}>{l.displayValue}</option>
                                        })
                                    }
                                </select>
                                <MDBTextArea
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Description'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setDescription(e.target.value)}
                                />
                                <MDBBtn
                                    className="mb-4  mx-5 w-100 subColorBackground"
                                    size='lg'
                                    onClick={onSignUpCompany}
                                >
                                    Sign up
                                </MDBBtn>
                                <p className='ms-5'>Don't have an account? <a href="/signup" className="link-info">Sign up
                                    here</a></p>
                            </div>
                        }
                        {
                            userType !== undefined && codeSent
                            &&
                            <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                                <p className="h5 subColorText ">The code was sent to your e-mail {email}. </p>
                                <p className="h5 subColorText ">Please check your e-mail and insert the code bellow</p>
                                <p></p>
                                <MDBInput
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Code'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    onChange={e => setCode(e.target.value)}
                                />
                                <MDBBtn
                                    className="mb-4 px-5 mx-5 w-100 subColorBackground"
                                    size='lg'
                                    onClick={activateProfile}
                                >
                                    Verify
                                </MDBBtn>

                            </div>
                        }

                    </MDBCol>
                </MDBRow>

            </MDBContainer>
        }
    </>

    )
}
export default SignUp;