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
import {get, put} from "../../methods";
import {token, user} from "../../context/Reducer";
import LoadingSpinner from "../loading/LoadingSpinner";

export default function EditProfileModal(props) {
    const {open, onClose} = props;
    const [locations, setLocations] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    const [location, setLocation] = useState(user.locationDTO.id);
    const [companyName, setCompanyName] = useState(user.companyName)
    const [description, setDescription] = useState(user.description)

    useEffect(() => {
        get('user-service/location/all', undefined, token)
            .then(response => {
                setLocations(response);
                setIsLoading(false);

                })
    }, [open])


    const handleOnClose = () => {
      onClose();
    }

    const handleOnSubmit = () => {
        console.info(location);
        console.info(locations)

        let selectedLocation = undefined;

        for(let l of locations) {
            // OVDJE MORA == JER JE LOCATION STRING A TREBA ID
            if (l.id == location) {
                selectedLocation = l;
            }
        }


        const editedUser = {
            "uuid": user.uuid,
            "companyName": companyName,
            "locationDTO": selectedLocation,
            "description": description
        }

        setIsLoading(true);

        put('user-service/user/' + user.uuid + '/update/COMPANY', editedUser, undefined, token)
            .then(response => {
                localStorage.setItem('currentUser', JSON.stringify( {
                    user: response,
                    token: token
                }));
                setIsLoading(false);
                onClose();
            })
    }

    return <>
        {
            isLoading ? <LoadingSpinner /> :
            <Modal show={open} onHide={onClose} size={'lg'}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit profile</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <MDBContainer fluid>
                        <MDBRow>
                            <div className='d-flex flex-column justify-content-center h-custom-2 w-75 pt-4'>
                                <MDBInput
                                    wrapperClass='mb-4 mx-5 w-100'
                                    label='Company name'
                                    id='formControlLg'
                                    type='text'
                                    size="lg"
                                    value={companyName}
                                    onChange={event => setCompanyName(event.target.value)}
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
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        }

    </>

}