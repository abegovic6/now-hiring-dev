import {MDBCard, MDBCardBody, MDBCardImage, MDBCardText, MDBCardTitle, MDBCol, MDBRow} from "mdb-react-ui-kit";
import locationIcon from "../../icons/geo-alt.svg";
import arrowIcon from "../../icons/caret-right.svg";
import React from "react";

export default function ConnectionRequestCard(props) {
    const {onAccept, onReject, creationUser, image} = props;

    return (
        <MDBCard style={{ maxWidth: '1223px', height: '310px', margin: "auto", marginTop: '10px'}}

        >
            <MDBRow className='g-0'>
                <MDBCol md='4'>
                    <MDBCardImage src={image} alt='...' height={300} />
                </MDBCol>
                <MDBCol md='8'>
                    <MDBCardBody style={{  marginTop: '40px', textAlign: 'left'}}>
                        <MDBCardTitle style={{fontSize: '40px', fontWeight: 'bold', letterSpacing: '-0.02em' }}>
                            {creationUser.displayValue}</MDBCardTitle>
                        <MDBCardText  style={{fontSize: '20px', fontWeight: 'bold', letterSpacing: '-0.02em' }}>
                            <MDBCardImage src={locationIcon} alt='...' height={20} style={{paddingRight: 5}} />
                            {creationUser.locationDTO.displayValue}
                        </MDBCardText>
                        <MDBCardText>
                            <small className='text-muted' >{creationUser.displayValue + " wants to connect!"}</small>
                        </MDBCardText>
                        <MDBCardText>
                            <div className="btn-group shadow-0" role="group" aria-label="Basic example">
                                <button onClick={onAccept} type="button" className="btn btn-outline-secondary" data-mdb-color="dark">Approve
                                </button>
                                <button onClick={onReject} type="button" className="btn btn-outline-secondary"
                                        data-mdb-color="dark">Reject
                                </button>
                            </div>
                        </MDBCardText>
                    </MDBCardBody>
                </MDBCol>
            </MDBRow>
        </MDBCard>
    );
}