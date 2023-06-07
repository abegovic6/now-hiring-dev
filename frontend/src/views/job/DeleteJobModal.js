import {Modal} from "react-bootstrap";
import {Button} from "reactstrap";
import { token } from "../../context/Reducer";

export default function DeleteJobModal(props) {
    const {open, onClose, jobId} = props;

    const handleOnDelete = () => {
        fetch('/job-service/job/deleteJob?id=' + jobId, {
            method: 'DELETE',
            headers: {
                'Content-type': 'application/json',
                'Authorization':  "Bearer " + token
            }
        }).then (response => {
            onClose();
        })
    }

    return <>
    
    {
        <Modal show={open} onHide={onClose} size={'lg'}>

                <Modal.Header closeButton>
                    <Modal.Title>Delete Job</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    Are you sure you want to delete this job ?
                </Modal.Body>


                <Modal.Footer>
                    <Button className='mainColorBackground blackText' onClick={onClose}>
                        Cancel
                    </Button>
                    <Button className='subColorBackground' onClick={handleOnDelete}>
                        Delete
                    </Button>
                </Modal.Footer>

        </Modal>
    }

</>
}