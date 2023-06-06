import React from 'react';
import {
    MDBCard,
    MDBCardTitle,
    MDBCardText,
    MDBCardBody,
    MDBCardImage,
    MDBRow,
    MDBCol
} from 'mdb-react-ui-kit';
import locationIcon from'../../icons/geo-alt.svg';
import arrowIcon from'../../icons/caret-right.svg';

export default function ListCard(params) {
    const {image, title, location, description, onArrowClick} = params;

    return (
        <MDBCard onClick={onArrowClick}
                 style={{ maxWidth: '1223px', height: '310px', margin: "auto", marginTop: '10px'}}

        >
            <MDBRow className='g-0'>
                <MDBCol md='4'>
                    <MDBCardImage src={image} alt='...' height={300} />
                </MDBCol>
                <MDBCol md='7'>
                    <MDBCardBody style={{  marginTop: '40px', textAlign: 'left'}}>
                        <MDBCardTitle style={{fontSize: '40px', fontWeight: 'bold', letterSpacing: '-0.02em' }}>
                            {title}</MDBCardTitle>
                        <MDBCardText  style={{fontSize: '24px', fontWeight: 'bold', letterSpacing: '-0.02em' }}>
                            <MDBCardImage src={locationIcon} alt='...' height={20} style={{paddingRight: 5}} />
                            {location}
                        </MDBCardText>
                        <MDBCardText>
                            <small className='text-muted' >{description}</small>
                        </MDBCardText>
                    </MDBCardBody>
                </MDBCol>
                <MDBCol md='1' style={{  margin: 'auto', height: '20%'}}>
                    <MDBCardImage src={arrowIcon} alt='...' height={70}  />
                </MDBCol>
            </MDBRow>
        </MDBCard>
    );
}