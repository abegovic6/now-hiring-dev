import React, {useState} from 'react'
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
import {post, put} from "../../methods";

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


    const onSignUp = async () => {
        if (password !== repeatedPassword) {
            alert("Passwords need to match!")
        } else {
            const user = {
                email: email,
                password: password,
                firstName: firstName,
                lastName: lastName,
                description: description,
                userType: userType,
                // todo: @abegovic fix the location!!!
                "locationDTO": {
                    "city": "Sarajevo",
                    "country": "Bosnia and Herzegovina"
                },
            }

            //const result = await post('api/user/upload', user);
            setCodeSent(true)
        }

    }

    const activateProfile = async () => {
        const result = await put('api/user/' + email + '/verify', undefined, {
            code: code
        });

        if (!result.errors) {
            navigate('/login');
            window.location.reload();
        } else {
            alert(result.errors[0])
        }

    }

    return (
        <MDBContainer fluid>
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
                                onClick={onSignUp}
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
                                onClick={onSignUp}
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
    )
}
export default SignUp;