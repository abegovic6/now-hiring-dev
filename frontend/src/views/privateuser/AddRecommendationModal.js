import React, { useEffect, useState } from "react";
import { Modal } from "react-bootstrap";
import { MDBContainer, MDBInput, MDBRow } from "mdb-react-ui-kit";
import { Select, SelectOption } from "mdb-react-ui-kit";
import { Button } from "reactstrap";
import { token, user } from "../../context/Reducer";
import { get, post, put } from "../../methods";
import LoadingSpinner from "../loading/LoadingSpinner";

function AddRecommendationModal(props) {
  const { currentProfile, open, onClose } = props;
  const [loggedUser, setLoggedUser] = useState();
  const [recommendedUser, setRecommendedUser] = useState();
  const [isLoading, setIsLoading] = useState(true);
  const [jobs, setJobs] = useState();
  const [selectedOption, setSelectedOption] = useState("");
  const [selectedJob, setSelectedJob] = useState();
  const [recommendations, setRecommendations] = useState();

  const handleClose = () => {
    onClose();
  };

  const handleSubmit = () => {
    let selectedJobObject = jobs.find((j) => `${j.id}` === selectedJob);

    function doesExist(r) {
      if (
        r.userEntity.id == loggedUser.id &&
        r.jobEntity.id == selectedJobObject.id &&
        r.recommendedUser.id == recommendedUser.id
      )
        return true;
      else return false;
    }

    let recommendationExist = recommendations.find(doesExist);
    if (recommendationExist) {
      alert(
        "Recommendation for user " +
          recommendedUser.name +
          " for a job " +
          selectedJobObject.name +
          " already exist!"
      );
    } else {
      let body = {
        description:
          "User " +
          user.displayValue +
          " recommended user " +
          props.currentProfile.displayValue +
          "for a job" +
          selectedJobObject.name,
        userEntity: loggedUser,
        jobEntity: selectedJobObject,
        recommendedUser: recommendedUser, //za sad
      };
      post(
        "http://localhost:3000/recommendation-service/recommendation/addNewRecommendation",
        body,
        undefined,
        token
      );
      alert(
        "You have recommended user " + recommendedUser.name + " for a job!"
      );
    }
    onClose();
  };

  const handleSelectChange = (event) => {
    setSelectedOption(event.target.value);
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
        setRecommendedUser(response);
      })
      .then(() => {
        return get(
          "http://localhost:3000/recommendation-service/job",
          undefined,
          token
        );
      })
      .then((response) => {
        setJobs(response);
        setSelectedJob(`${response[0].id}`);
        //setIsLoading(false);
      })
      .then(() => {
        return get(
          "http://localhost:3000/recommendation-service/recommendation",
          undefined,
          token
        );
      })
      .then((response) => {
        setRecommendations(response);
        setIsLoading(false);
      });
    //};
    //fetchJobs.catch((e) => console.error(e));
  }, []);

  const [fetchingData, setFetchingData] = React.useState(false);

  useEffect(() => {
    if (fetchingData) {
      // ovdje fetchas
      // ako ne bude radilo sa samo funkcijom
    }
  }, [fetchingData]);
  return (
    <>
      {isLoading ? (
        <LoadingSpinner />
      ) : (
        <Modal show={open} onHide={onClose}>
          <Modal.Header closeButton>
            <Modal.Title>Add recommendation</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <p>Recommend user {props.currentProfile.displayValue} for a job</p>
            <select
              value={selectedOption}
              onChange={(event) => {
                setSelectedJob(event.target.value);
              }}
            >
              <option value={!selectedJob ? "" : selectedJob}>
                {!selectedJob
                  ? "Select a job"
                  : jobs.find((j) => `${j.id}` === selectedJob)?.name}
              </option>
              {jobs &&
                jobs.map((job) => {
                  if (job.id === selectedJob.id) {
                    return (
                      <option selected value={job.id}>
                        {job.name}
                      </option>
                    );
                  }
                  return <option value={job.id}>{job.name}</option>;
                })}
            </select>
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

export default AddRecommendationModal;
