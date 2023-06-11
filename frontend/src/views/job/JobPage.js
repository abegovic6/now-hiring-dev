import React from "react";
import { token, user } from "../../context/Reducer";
import LoadingSpinner from "../loading/LoadingSpinner";
import { useEffect, useState } from "react";
import { get } from "../../methods";
import profilePlaceholder from "../../icons/profileplaceholder.png";
import editSign from "../../icons/pencil.svg";
import deleteSign from "../../icons/trash.svg";
import { Button } from "react-bootstrap";
import { Grid, Header, Image, Segment } from "semantic-ui-react";
import { useNavigate, useParams } from "react-router-dom";
import List from "../listpage/List";

import {
  MDBCol,
  MDBContainer,
  MDBRow,
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardImage,
  MDBBtn,
  MDBListGroup,
  MDBListGroupItem,
  MDBIcon,
  MDBInput,
} from "mdb-react-ui-kit";
import EditJobModal from "./EditJobModal";
import DeleteJobModal from "./DeleteJobModal";
import JobApplicationModal from "./JobApplicationModal";

export default function JobPage() {
  const params = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [job, setJob] = useState({});
  const [company, setCompany] = useState({});
  const [editJob, setEditJob] = useState(false);
  const [deleteJob, setDeleteJob] = useState(false);
  const [jobApply, setJobApply] = useState(false);
  const [isMy, setIsMy] = useState(false);
  const [applications, setApplications] = useState([]);
  const [getErrors, setGetErrors] = useState("");
  const [hasErrors, setHasErrors] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    setIsLoading(true);
    get(
      "http://localhost:3000/job-service/job/get?id=" + params.id,
      undefined,
      token
    )
      .then((response) => {
        if (response.status === 404) {
          setHasErrors(true);
          setGetErrors(response.errors);
          return;
        }
        setJob(response);
        return get(
          "user-service/user/uuid/" + response.companyId,
          undefined,
          token
        );
      })
      .then((response) => {
        setIsMy(response.uuid === user.uuid);
        setCompany(response);
        return get(
          "http://localhost:3000/job-service/application/getAppsForJob?id=" +
            params.id,
          undefined,
          token
        );
      })
      .then((response) => {
        setApplications(response);
        setIsLoading(false);
      });
  }, []);

  const onEditJobClose = () => {
    setEditJob(false);
    window.location.reload();
  };

  const onApplyJobClose = () => {
    setJobApply(false);
    window.location.reload();
  };

  const onDeleteJobClose = () => {
    setDeleteJob(false);
    navigate("/mycompany");
    window.location.reload();
  };

  const handleCheckUserProfile = (userId) => {
    navigate("/profile/" + userId);
  };

  return (
    <>
      {isLoading ? (
        <LoadingSpinner />
      ) : (
        <section style={{ backgroundColor: "#eee" }}>
          <MDBContainer className="py-5">
            <MDBRow>
              <MDBCol lg="4">
                <MDBCard className="mb-4">
                  <MDBCardBody className="text-center">
                    <MDBCardImage
                      src={profilePlaceholder}
                      alt="avatar"
                      className="rounded-circle"
                      style={{ width: "150px" }}
                      fluid
                    />
                    <p className="text-muted mb-1">{company.displayValue}</p>
                    <p className="text-muted mb-1">
                      {company.locationDTO.displayValue}
                    </p>
                    <p className="text-muted mb-1">
                      {"Connections: " + company.connections.length}
                    </p>
                  </MDBCardBody>
                </MDBCard>
              </MDBCol>

              <MDBCol lg="8">
                <MDBCard className="mb-4">
                  <MDBCardBody>
                    {isMy && (
                      <MDBRow>
                        <MDBCol>
                          <div
                            className="containerButtonText mx-2"
                            style={{
                              display: "flex",
                              gap: "10px",
                              justifyContent: "flex-end",
                            }}
                          >
                            {isMy && (
                              <>
                                <MDBCardImage
                                  src={editSign}
                                  alt="..."
                                  height={20}
                                  onClick={() => setEditJob(true)}
                                />

                                <MDBCardImage
                                  src={deleteSign}
                                  alt="..."
                                  height={20}
                                  onClick={() => setDeleteJob(true)}
                                />
                              </>
                            )}
                          </div>
                        </MDBCol>
                      </MDBRow>
                    )}
                    <MDBRow>
                      <MDBCol sm="3">
                        <MDBCardText>Job Title</MDBCardText>
                      </MDBCol>
                      <MDBCol sm="9">
                        <MDBCardText className="text-muted">
                          {job.title}
                        </MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr />
                    <MDBRow>
                      <MDBCol sm="3">
                        <MDBCardText>Job Type</MDBCardText>
                      </MDBCol>
                      <MDBCol sm="9">
                        <MDBCardText className="text-muted">
                          {job.type}
                        </MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr />
                    <MDBRow>
                      <MDBCol sm="3">
                        <MDBCardText>Job Location</MDBCardText>
                      </MDBCol>
                      <MDBCol sm="9">
                        <MDBCardText className="text-muted">
                          {job.location}{" "}
                        </MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr />
                    <MDBRow>
                      <MDBCol sm="3">
                        <MDBCardText>Job Description</MDBCardText>
                      </MDBCol>
                      <MDBCol sm="9">
                        <MDBCardText className="text-muted">
                          {job.description}
                        </MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr />
                    <MDBRow>
                      <MDBCol sm="3">
                        <MDBCardText>Valid-To</MDBCardText>
                      </MDBCol>
                      <MDBCol sm="9">
                        <MDBCardText className="text-muted">
                          {job.validTo}
                        </MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    {!isMy && !job.expired && (
                      <>
                        <hr />
                        <MDBRow>
                          <div className="text-center">
                            <Button
                              variant="secondary"
                              style={{ width: "50%" }}
                              onClick={() => setJobApply(true)}
                            >
                              APPLY NOW !
                            </Button>
                          </div>
                        </MDBRow>
                      </>
                    )}
                    {job.expired && (
                      <>
                        <hr />
                        <span
                          style={{
                            color: "red",
                            fontSize: "1.2em",
                            fontWeight: "bold",
                            letterSpacing: "0.1em",
                          }}
                        >
                          EXPIRED
                        </span>
                      </>
                    )}
                  </MDBCardBody>
                </MDBCard>
                {isMy && (
                  <MDBCard className="mb-4 mb-md-0">
                    <MDBCardBody>
                      <MDBCardText>Applications</MDBCardText>
                      {applications &&
                        applications.map((application) => {
                          return (
                            <>
                              <>
                                <MDBCard>
                                  <MDBCardBody>
                                    <MDBRow>
                                      <MDBCol sm="3">
                                        <MDBCardText>Name</MDBCardText>
                                      </MDBCol>
                                      <MDBCol sm="9">
                                        <MDBCardText className="text-muted">
                                          {application.firstName}{" "}
                                          {application.lastName}
                                        </MDBCardText>
                                      </MDBCol>
                                    </MDBRow>
                                    <MDBRow>
                                      <hr className="mt-2" />
                                      <MDBCol sm="3">
                                        <MDBCardText>Cover Letter</MDBCardText>
                                      </MDBCol>
                                      <MDBCol sm="9">
                                        <MDBCardText className="text-muted">
                                          {application.coverLetter}
                                        </MDBCardText>
                                      </MDBCol>
                                    </MDBRow>

                                    <MDBRow>
                                      <div className="text-end mt-3">
                                        <Button
                                          variant="secondary"
                                          style={{ width: "50%" }}
                                          onClick={() =>
                                            handleCheckUserProfile(
                                              application.userId
                                            )
                                          }
                                        >
                                          CHECK USER PROFILE
                                        </Button>
                                      </div>
                                    </MDBRow>
                                  </MDBCardBody>
                                </MDBCard>
                                <br />
                              </>
                            </>
                          );
                        })}
                    </MDBCardBody>
                  </MDBCard>
                )}
              </MDBCol>
            </MDBRow>
          </MDBContainer>
        </section>
      )}
      {isMy && editJob && (
        <EditJobModal
          open={editJob}
          onClose={onEditJobClose}
          title={job.title}
          type={job.type}
          description={job.description}
          validTo={job.validTo}
          jobId={job.id}
        />
      )}
      {isMy && deleteJob && (
        <DeleteJobModal
          open={deleteJob}
          onClose={onDeleteJobClose}
          jobId={job.id}
        />
      )}
      {!isMy && jobApply && (
        <JobApplicationModal
          open={jobApply}
          onClose={onApplyJobClose}
          jobId={job.id}
        />
      )}
    </>
  );
}
