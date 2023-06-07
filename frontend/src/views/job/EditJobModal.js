import {Modal} from "react-bootstrap";
import {Button} from "reactstrap";
import {
    MDBContainer,
    MDBInput,
    MDBRow,
    MDBDropdown,
    MDBDropdownItem,
    MDBDropdownMenu,
    MDBDropdownToggle,
    MDBTextArea
} from "mdb-react-ui-kit";
import { useState } from "react";
import Form from 'react-bootstrap/Form';
import {get, post} from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";
import { token } from "../../context/Reducer";

export default function EditJobModal(props) {
    const {open, onClose, title, type, description, validTo, jobId} = props;
    const [newValidTo, setValidTo] = useState(validTo)
    const [newTitle, setNewTitle] = useState(title)
    const [newType, setNewType] = useState(type)
    const [newDescription, setNewDescription] = useState(description)

    const validateStates = () => {
        let errors = ""
        if(newTitle === "") errors = errors = errors.concat("Please provide job title\n");
        if(newType === "") errors = errors = errors.concat("Please provide job type\n");
        if(newValidTo === "") errors = errors = errors.concat("Please provide valid-to date\n");
        return errors
    }

    const handleOnSubmit = () => {
        let errors = validateStates();

        let newJob = {
            "id":jobId,
            "title":newTitle,
            "type":newType,
            "description":newDescription,
            "validTo":newValidTo
        }

        if (errors === "") {
            post('/job-service/job/update', newJob, undefined, token)
            .then(
                response => {
                    onClose();
                }
            )
        }
        else{
            alert(errors);
        }

    }
    return <>
    
    {
        <Modal show={open} onHide={onClose} size={'lg'}>

                <Modal.Header closeButton>
                    <Modal.Title>Edit Job</Modal.Title>
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
                                    value={newTitle}
                                    onChange={event => setNewTitle(event.target.value)}
                                />
                                <MDBInput
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Job Type'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    value={newType}
                                    onChange={event => setNewType(event.target.value)}
                                />
                                <MDBTextArea
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Description'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    value={newDescription}
                                    onChange={event => setNewDescription(event.target.value)}
                                />
                                
                                <Form.Group className="mb-4">
                                            
                                            <Form.Control type="date" 
                                            value={newValidTo}
                                            className="mx-5 w-100"
                                                          onChange={e => setValidTo(e.target.value)}/>
                                            <Form.Label className="mb-4 mx-5 w-100">Valid To</Form.Label>
                                </Form.Group>

                            </div>
                        </MDBRow>
                    </MDBContainer>
                </Modal.Body>


                <Modal.Footer>
                    <Button className='mainColorBackground blackText' onClick={onClose}>
                        Cancel
                    </Button>
                    <Button className='subColorBackground' onClick={handleOnSubmit}>
                        Save
                    </Button>
                </Modal.Footer>

        </Modal>
    }

</>
}