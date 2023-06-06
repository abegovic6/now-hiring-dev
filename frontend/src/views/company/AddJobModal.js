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

export default function AddJobModal(props) {
    const {open, onClose} = props;
    const [locations, setLocations] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const [location, setLocation] = useState(user.locationDTO.id);
    const [companyName, setCompanyName] = useState(user.companyName)
    const [description, setDescription] = useState(user.description)

    const [type, setType] = useState("")
    const [title, setTitle] = useState("")
    const [validTo, setValidTo] = useState("")

    useEffect(() => {
        get('user-service/user/auth/locations', undefined)
            .then(response => {
                setLocations(response);
                setIsLoading(false);

                })
    }, [open])


    const handleOnClose = () => {
      onClose();
    }


    const validateStates = () => {
        let errors = ""
        if(title === "") errors = errors = errors.concat("Please provide job title\n");
        if(type === "") errors = errors = errors.concat("Please provide job type\n");
        if(validTo === "") errors = errors = errors.concat("Please provide valid-to date\n");
        return errors
    }

    const handleOnSubmit = () => {

        let errors = validateStates();
        if (errors === "") {


        console.info(location);
        console.info(locations)

        let selectedLocation = undefined;

        for(let l of locations) {
            // OVDJE MORA == JER JE LOCATION STRING A TREBA ID
            if (l.id == location) {
                selectedLocation = l;
            }
        }


        const newJob = {
            "companyId": user.uuid,
            "title":title,
            "type":type,
            "location":selectedLocation.city,
            "validTo":validTo,
            "description": description
        }

        setIsLoading(true);

        post('job-service/job/add', newJob, undefined, token)
            .then(response => {
                setIsLoading(false);
                onClose();
            })

        }
        else{
            alert(errors);
        }
    }

    return <>
        {
            isLoading ? <LoadingSpinner /> :
            <Modal show={open} onHide={onClose} size={'lg'}>
                <Modal.Header closeButton>
                    <Modal.Title>Add a new job</Modal.Title>
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
                                    value={title}
                                    onChange={event => setTitle(event.target.value)}
                                />
                                <MDBInput
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Job Type'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    value={type}
                                    onChange={event => setType(event.target.value)}
                                />
                                <MDBTextArea
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Description'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    value={description}
                                    onChange={event => setDescription(event.target.value)}
                                />
                                
                                <Form.Group className="mb-4">
                                            
                                            <Form.Control type="date"
                                            className="mx-5 w-100"
                                                          onChange={e => setValidTo(e.target.value)}/>
                                            <Form.Label className="mb-4 mx-5 w-100">Valid To</Form.Label>
                                </Form.Group>

                                <select className="mb-4 mx-5 w-100 form-select" aria-label="Default select example"
                                    onChange={event => {
                                        setLocation(event.target.value)
                                    }
                                    }>
                                    {
                                        locations && locations.map(l => {
                                            if (l.id === location.id) {
                                                return <option selected value={l.id}>{l.displayValue}</option>
                                            }
                                            return <option value={l.id}>{l.displayValue}</option>
                                        })
                                    }
                                </select>

                            </div>
                        </MDBRow>
                    </MDBContainer>
                </Modal.Body>
                <Modal.Footer>
                    <Button className='mainColorBackground blackText' onClick={handleOnClose}>
                        Cancel
                    </Button>
                    <Button className='subColorBackground' onClick={handleOnSubmit}>
                        Add
                    </Button>
                </Modal.Footer>
            </Modal>
        }

    </>

}