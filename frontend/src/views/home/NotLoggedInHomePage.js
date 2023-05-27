import {Grid, Header, Segment} from "semantic-ui-react";
import {MDBBtn} from "mdb-react-ui-kit";
import React from "react";

export default function NotLoggedInHomePage () {
    return (
        <Segment style={{ padding: '3em 10em' }} className={"darkColorBackground mainColorText"} vertical>
            <Grid container stackable verticalAlign='middle'>
                <Grid.Row>
                    <Grid.Column width={8}>
                        <Header as='h1' style={{ fontSize: '2em', fontWeight: 'bold' }}>
                            Welcome to NowHiringDev.ba!
                        </Header>
                        <p style={{ fontSize: '1.33em' }}>
                            Our cutting-edge platform is the ultimate destination for developers seeking exciting career opportunities and companies searching for top-notch talent to join their teams. Whether you're a seasoned professional or just starting your journey in the tech industry, TechConnect is here to streamline your job search or recruitment process.
                        </p>
                        <Header as='h3' style={{ fontSize: '2em' }}>
                            For Developers
                        </Header>
                        <p style={{ fontSize: '1.33em' }}>
                            NowHiringDev.ba provides an intuitive and comprehensive job search experience, tailored specifically to the needs of developers. Our platform hosts a vast database of job listings spanning various industries, technologies, and experience levels. Whether you specialize in web development, artificial intelligence, mobile applications, or any other niche, we have opportunities that match your skills and interests.
                        </p>
                        <Header as='h3' style={{ fontSize: '2em' }}>
                            For Companies
                        </Header>
                        <p style={{ fontSize: '1.33em' }}>
                            NowHiringDev.ba empowers companies of all sizes to connect with highly skilled developers who are passionate about their craft. Our platform provides a seamless recruitment process, enabling you to find the perfect fit for your team efficiently and effectively.
                        </p>
                    </Grid.Column>
                    <Grid.Column floated='right' width={6}>
                        <p></p>
                        <MDBBtn
                            className=" mx-2 w-50 subColorBackground"
                            size='lg'
                        >
                            Join today
                        </MDBBtn>
                        <p></p>
                        <p style={{ fontSize: '1.33em' }}>
                            Join NowHiringDev.ba today and unlock a world of opportunities for developers and employers alike. Seamlessly connect with the tech community, explore career possibilities, and discover the perfect match to take your projects and careers to new heights.
                        </p>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Segment>

    );
}