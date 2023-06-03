import {Modal} from "react-bootstrap";
import {MDBContainer, MDBInput, MDBRow} from "mdb-react-ui-kit";
import {Button} from "reactstrap";
import React from "react";
import MonthPicker from "../components/MonthPicker";

export default function AddExperienceModal(props) {
    const {open, onClose} = props;

    const handleClose = () => {
      onClose();
    }

    const handleSubmit = () => {
        onClose();
    }

    return <Modal show={open} onHide={onClose}>
        <Modal.Header closeButton>
            <Modal.Title>Add experience</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <MDBContainer fluid>
                <MDBRow>
                    <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                        <MDBInput
                            wrapperClass='mb-4 mx-5 w-100'
                            label='Title'
                            id='formControlLg'
                            type='text'
                            size="lg"
                        />
                        <MDBInput
                            wrapperClass='mb-4 mx-5 w-100'
                            label='Description'
                            id='formControlLg'
                            type='text'
                            size="lg"
                        />
                        <MonthPicker />
                    </div>
                </MDBRow>
            </MDBContainer>
        </Modal.Body>
        <Modal.Footer>
            <Button className='mainColorBackground blackText' onClick={handleSubmit}>
                Cancel
            </Button>
            <Button className='subColorBackground' onClick={handleSubmit}>
                Save Changes
            </Button>
        </Modal.Footer>
    </Modal>
}