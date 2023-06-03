import React, { useState } from 'react'
import './Login.css'
import './../../App.css';
import {loginUser, useAuthDispatch} from "../../context";
import {useNavigate} from "react-router-dom";
import {
    MDBBtn,
    MDBContainer,
    MDBRow,
    MDBCol,
    MDBInput
}
    from 'mdb-react-ui-kit';
import justAPicture from'../../icons/imagee.png';

const Login = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const dispatch = useAuthDispatch()
    const handleSubmit = (e) => {
        e.preventDefault()
        loginUser(dispatch, {email, password}).then(data => {
            if (!data.errors) {
                navigate('/');
                window.location.reload();
            } else if (data){
                alert(data.errors[0])
            }
        })
    }

    return (<MDBContainer fluid>
        <MDBRow>
            <MDBCol sm='5' className='d-none d-sm-block px-0'>
                <img src={justAPicture} className="w-100" style={{objectFit: 'cover', objectPosition: 'left'}}  alt="fireSpot"/>
            </MDBCol>
            <MDBCol sm='7'>
                <div className='d-flex ps-5 flex-row pt-5 justify-content-center w-75 '>
                    <p className="h1 fw-bold subColorText ">LOG IN</p>
                </div>
                <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                    <MDBInput
                        wrapperClass='mb-4 mx-5 w-100'
                        label='Email address'
                        id='formControlLg'
                        type='email'
                        size="lg"
                        onChange={e => setEmail(e.target.value)}
                    />
                    <MDBInput
                        wrapperClass='mb-4 mx-5 w-100'
                        label='Password'
                        id='formControlLg'
                        type='password'
                        size="lg"
                        onChange={e => setPassword(e.target.value)}
                    />
                    <MDBBtn
                        className="mb-4 px-5 mx-5 w-100 subColorBackground"
                        size='lg'
                        onClick={handleSubmit}
                    >
                        Login
                    </MDBBtn>
                    <p className='ms-5'>Don't have an account? <a href="/signup" className="link-info ">Sign up here</a></p>
                </div>

            </MDBCol>

        </MDBRow>

    </MDBContainer>
    )}
export default Login;