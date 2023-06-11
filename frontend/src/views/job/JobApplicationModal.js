import {Modal} from "react-bootstrap";
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
import {Button} from "reactstrap";
import React, {useEffect, useState} from "react";
import {get, post} from "../../methods";
import {token, user} from "../../context/Reducer";
import LoadingSpinner from "../loading/LoadingSpinner";
import Form from 'react-bootstrap/Form';

export default function JobApplicationModal(props){
    const {open, onClose, jobId, companyId} = props;
    const [coverLetter, setCoverLetter] = useState('')
    const [done, setDone] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    
    const [alreadyApplied, setAlreadyApplied] = useState(false);

    useEffect(() => {
        get('/job-service/application/getAppsForUser?id=' + user.uuid, undefined, token)
            .then(response => {
                const hasJobId = response.some(item => item.jobId === jobId);
                setAlreadyApplied(hasJobId)
                setIsLoading(false);
                })
    }, [open])

    const handleOnClose = () => {
        onClose();
      }

    const handleOnSubmit = () => {

        setIsLoading(true)
        let application = {
            "jobId":jobId,
            "userId":user.uuid,
            "coverLetter":coverLetter
        }

        post('/job-service/application/create', application, undefined, token)
        .then(
            response => {
                setDone(true)
                setIsLoading(false)
                
            }
        )
    }

    return <>
    {
        <Modal show={open} onHide={onClose} size={'lg'}>
            <Modal.Header closeButton>
                <Modal.Title>Job Application</Modal.Title>
            </Modal.Header>
            {
                !done && !isLoading && !alreadyApplied &&
                <><Modal.Body>
                        <MDBContainer fluid>
                            Your profile information will be automatically populated, facilitating the process of completing your application. A cover letter is the only additional requirement.
                            <MDBRow>
                                <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                                    <MDBTextArea
                                        wrapperClass='mb-4 mx-5 w-100'
                                        label='Cover Letter'
                                        id='formControlLg'
                                        type='text'
                                        size="lg"
                                        onChange={event => setCoverLetter(event.target.value)} />
                                </div>

                            </MDBRow>

                        </MDBContainer>
                    </Modal.Body><Modal.Footer>
                            <Button className='mainColorBackground blackText' onClick={handleOnClose}>
                                Cancel
                            </Button>
                            <Button className='subColorBackground' onClick={handleOnSubmit}>
                                Submit
                            </Button>
                        </Modal.Footer></>
            }
            {
                done && !isLoading && !alreadyApplied &&
                <><Modal.Body>
                        <MDBContainer fluid>
                            You have successfully applied for this job!
                        </MDBContainer>
                    </Modal.Body><Modal.Footer>
                            <Button className='mainColorBackground blackText' onClick={handleOnClose}>
                                Close
                            </Button>
                </Modal.Footer></>
            }
            {
                isLoading &&
                <LoadingSpinner></LoadingSpinner>
            }
            {
                alreadyApplied && !isLoading &&
                <><Modal.Body>
                        <MDBContainer fluid>
                            You have already applied for this job!
                        </MDBContainer>
                    </Modal.Body><Modal.Footer>
                            <Button className='mainColorBackground blackText' onClick={handleOnClose}>
                                Close
                            </Button>
                </Modal.Footer></>
            }
            
        </Modal>
    }</>
}