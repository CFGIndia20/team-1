import React, { useState, useEffect } from "react";
import * as d3 from "d3";
import firebase from "../../firebaseConfig";
import { Container, Col, Card, Row, Table } from 'reactstrap';
import SideNav from './SideNav';
import BarChart from '../charts/BarChart';
import Pie from '../charts/Pie';
import Line from '../charts/Line';
import {LineChart, PieChart} from 'react-chartkick';
import 'chart.js';
import {
  CircularProgressbar,
  buildStyles,
} from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';


const db = firebase.firestore();
export default function DashHome() {
  
  const generateData = (value, length = 5) =>
    d3.range(length).map((item, index) => ({
      date: index,
      value: value === null || value === undefined ? Math.random() * 100 : value
    }));
  var genArray = [];
  db.collection('test 2').get().then(res=>{
    res.docs.forEach(doc=>{
      const x = doc.data();
      const y = Object.values(x);
      for(let i = 0; i<y.length;i++){
        genArray.push(y[i]);
      }
    })
  })
  console.log(genArray)
 
  var v1  = 55;
  var v2  = 64;
  var v3 = 43;
  var v4 = 75;
  var v5 = 66;
  const [data, setData] = useState(generateData(0));
  const changeData = () => {
    setData(generateData());
  };

  useEffect(
    () => {
      setData(generateData());
    },
    [!data]
  );
  
const dummyData  = [
  {"name":"Patients Discharged", "data": {"Jan":24,"Feb":53,"Mar":44,"Apr":54,"May":88}},
  {"name":"Patients Who filled feedback", "data": {"Jan":18,"Feb":32,"Mar":38,"Apr":50,"May":87}}
];
  return (
    <div className="dashboard-wrap">

      <SideNav />

      <Container className="right-side">

        <h4 className="greeting">Welcome</h4>

        <Row className="spacer">
          <Col md="4 text-center">
            <div className="piechart-bg">
            <PieChart data={[["Cleanliness", 11], ["Treatment", 64], ["Service",5],["Recreation",11],["management temper",6],["quick response",3]]} />
             
            </div>
          </Col>

          <Col md="8">
          <LineChart data={dummyData} />
          </Col>
        </Row>

        <Row>
          <Col md="7">
            <Table striped>
              <thead>
                <tr>
                  <th>Center Name</th>
                  <th>Best In</th>
                  <th>Needs Improvements</th>
                </tr>
              </thead>
              <tbody>
                <tr>

                  <td>Bengaluru</td>
  <td>Cleanliness {v2}</td>
                  <td>Quick Response</td>
                </tr>
                <tr>

                  <td>Mumbai</td>
  <td>Cleanliness {v3}</td>
                  <td>Facilities</td>
                </tr>
                <tr>

                  <td>Delhi</td>
  <td>Treatment {v4}</td>
                  <td>Cleanliness</td>
                </tr>
                <tr>
                  <td>Chennai</td>
  <td>Service {v2}</td>
                  <td>Management temper</td>
                </tr>
                <tr>
                  <td>Hydrabad</td>
  <td>Recreation {v4}</td>
                  <td>Treatment</td>
                </tr>
              </tbody>
            </Table>
          </Col>


          <Col md="5">
            <Card>
            Average Ratings for all the feedback fields
            <LineChart data={{"Cleanliness":4,"Quick Response":3,"Treatment":4,"Service":2,"Recreation":3,"management temper":3}} />


            </Card>
          </Col>

        </Row>
      </Container>

    </div>
  );
}