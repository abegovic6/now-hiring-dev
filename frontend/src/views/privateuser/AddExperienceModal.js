import { Modal } from "react-bootstrap";
import { MDBContainer, MDBInput, MDBRow, MDBTextArea } from "mdb-react-ui-kit";
import { Button } from "reactstrap";
import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import { token, user } from "../../context/Reducer";
import { post } from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";

export default function AddExperienceModal(props) {
  const { open, onClose } = props;
  const [title, setTile] = useState("");
  const [description, setDescription] = useState("");
  const [dateFrom, setDateFrom] = useState("");
  const [dateUntil, setDateUntil] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleFromDateChange = (date) => {
    setDateFrom(date.target.value);
  };

  const handleUntilDateChange = (date) => {
    setDateUntil(date.target.value);
  };

  const handleClose = () => {
    onClose();
  };

  const handleSubmit = () => {
    setIsLoading(true);
    const validFrom = new Date(dateFrom);
    const validTo = new Date(dateUntil);
    const experience = {
      user: {
        uuid: user.uuid,
        email: user.email,
      },
      title: title,
      description: description,
      startingMonth: validFrom.getMonth() + 1,
      startingYear: validFrom.getFullYear(),
      endMonth: validTo.getMonth() + 1,
      endYear: validTo.getFullYear(),
    };

    post("/feature-service/experience/add", experience, undefined, token).then(
      (response) => {
        setIsLoading(false);
        if (response.errors) {
          alert(response.errors[0]);
        } else {
          alert("Experience added!");
          window.location.reload();
        }
      }
    );
    onClose();
    window.location.reload();
  };

  return (
    <>
      {isLoading ? (
        <LoadingSpinner />
      ) : (
        <Modal show={open} onHide={onClose}>
          <Modal.Header closeButton>
            <Modal.Title>Add experience</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <MDBContainer fluid>
              <MDBRow>
                <div className="d-flex flex-column justify-content-center h-custom-2 w-75 pt-4">
                  <MDBInput
                    wrapperClass="mb-4 mx-5 w-100"
                    label="Title"
                    id="formControlLg"
                    type="text"
                    size="lg"
                    onChange={(event) => setTile(event.target.value)}
                  />
                  <MDBTextArea
                    wrapperClass="mb-4 mx-5 w-100"
                    label="Description"
                    id="formControlLg"
                    type="text"
                    size="lg"
                    onChange={(event) => setDescription(event.target.value)}
                  />
                  <Form.Group className="mb-4">
                    <Form.Control
                      type="date"
                      className="mx-5 w-100"
                      onChange={handleFromDateChange}
                    />
                    <Form.Label className="mb-4 mx-5 w-100">
                      Valid Until
                    </Form.Label>
                  </Form.Group>

                  <Form.Group className="mb-4">
                    <Form.Control
                      type="date"
                      className="mx-5 w-100"
                      onChange={handleUntilDateChange}
                    />
                    <Form.Label className="mb-4 mx-5 w-100">
                      Valid To
                    </Form.Label>
                  </Form.Group>
                </div>
              </MDBRow>
            </MDBContainer>
          </Modal.Body>
          <Modal.Footer>
            <Button
              className="mainColorBackground blackText"
              onClick={handleClose}
            >
              Cancel
            </Button>
            <Button className="subColorBackground" onClick={handleSubmit}>
              Save Changes
            </Button>
          </Modal.Footer>
        </Modal>
      )}
    </>
  );
}
