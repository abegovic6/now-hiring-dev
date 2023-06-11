import React, { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import { Button } from "reactstrap";
import { MDBContainer, MDBInput, MDBRow, MDBCardTitle } from "mdb-react-ui-kit";
import { token, user } from "../../context/Reducer";
import { get, post, put } from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";

function AddSkillsModal(props) {
  const { open, onClose } = props;
  const [isLoading, setIsLoading] = useState(true);
  const [skill, setSkill] = useState("");
  const [loggedUser, setLoggedUser] = useState();

  const handleClose = () => {
    onClose();
  };

  const handleSubmit = () => {
    let body = {
      user: loggedUser,
      title: skill,
    };
    post(
      "http://localhost:3000/feature-service/skill/add",
      body,
      undefined,
      token
    );
    onClose();
    window.location.reload();
  };

  const handleChange = (event) => {
    setSkill(event.target.value);
  };

  useEffect(() => {
    setIsLoading(true);
    get(
      "http://localhost:3000/feature-service/user/uuid/" + user.uuid,
      undefined,
      token
    ).then((response) => {
      setLoggedUser(response);
      setIsLoading(false);
    });
  }, []);

  return (
    <>
      {/* {isLoading ? (
        <LoadingSpinner />
      ) : ( */}
      <Modal show={open} onHide={onClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add skills</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <MDBContainer fluid>
            <MDBRow>
              <div className="d-flex flex-column justify-content-center h-custom-2 w-75 pt-4">
                <MDBInput
                  wrapperClass="mb-4 mx-5 w-100"
                  label="Comment"
                  id="commentId"
                  type="text"
                  size="lg"
                  onChange={(e) => handleChange(e)}
                />
              </div>
            </MDBRow>
          </MDBContainer>
        </Modal.Body>
        <Modal.Footer>
          <Button className="mainColorBackground blackText" onClick={onClose}>
            Cancel
          </Button>
          <Button className="subColorBackground" onClick={handleSubmit}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      {/* )} */}
    </>
  );
}

export default AddSkillsModal;
