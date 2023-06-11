import React, { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import { Button } from "reactstrap";
import { MDBContainer, MDBInput, MDBRow, MDBCardTitle } from "mdb-react-ui-kit";
import { token, user } from "../../context/Reducer";
import { get, post, put } from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";

function AddReviewModal(props) {
  const { currentProfile, open, onClose } = props;
  const [loggedUser, setLoggedUser] = useState();
  const [reviewedUser, setReviewedUser] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const [comment, setComment] = useState("");
  const [rating, setRating] = useState(0);

  const handleClose = () => {
    onClose();
  };

  const handleSubmit = () => {
    console.log(
      "Podaci koji mi trebaju: " +
        loggedUser.email +
        " " +
        reviewedUser.email +
        " " +
        comment +
        " " +
        rating
    );
    let body = {
      creator: loggedUser,
      receiver: reviewedUser,
      comment: comment,
      number: rating,
    };

    post(
      "http://localhost:3000/recommendation-service/review/add",
      body,
      undefined,
      token
    );

    alert("You have reviewed a user " + reviewedUser.name);
    onClose();
  };
  const handleChange = (event) => {
    setComment(event.target.value);
  };
  const handleRatingChange = (newRating) => {
    setRating(newRating);
  };

  useEffect(() => {
    //const fetchJobs = async () => {
    setIsLoading(true);
    get(
      "http://localhost:3000/recommendation-service/user/email/" + user.email,
      undefined,
      token
    )
      .then((response) => {
        setLoggedUser(response);
      })
      .then(() => {
        return get(
          "http://localhost:3000/recommendation-service/user/email/" +
            props.currentProfile.email,
          undefined,
          token
        );
      })
      .then((response) => {
        setReviewedUser(response);
        setIsLoading(false);
      });
  }, []);

  return (
    <>
      {isLoading ? (
        <LoadingSpinner />
      ) : (
        <Modal show={open} onHide={onClose}>
          <Modal.Header closeButton>
            <Modal.Title>Add review</Modal.Title>
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
                  <div className="mb-4 mx-5 w-100">
                    <p>Rating: {rating}</p>
                    {[1, 2, 3, 4, 5].map((value) => (
                      <span
                        key={value}
                        onClick={() => handleRatingChange(value)}
                        style={{
                          cursor: "pointer",
                          color: value <= rating ? "yellow" : "gray",
                        }}
                      >
                        &#9733;
                      </span>
                    ))}
                  </div>
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
      )}
    </>
  );
}

export default AddReviewModal;
