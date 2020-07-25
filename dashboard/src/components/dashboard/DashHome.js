import React, { useState, useEffect } from "react";
import * as d3 from "d3";
import { Container, Col, Card, Row, Table } from 'reactstrap';
import SideNav from './SideNav';
import BarChart from '../charts/BarChart';
import Pie from '../charts/Pie';

import {
  CircularProgressbar,
  buildStyles,
} from 'react-circular-progressbar';
import 'react-circular-progressbar/dist/styles.css';



export default function DashHome() {
  const generateData = (value, length = 5) =>
    d3.range(length).map((item, index) => ({
      date: index,
      value: value === null || value === undefined ? Math.random() * 100 : value
    }));

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


  return (
    <div className="dashboard-wrap">

      <SideNav />

      <Container className="right-side">

        <h4 className="greeting">Welcome</h4>

        <Row className="spacer">
          <Col md="4 text-center">
            <div className="piechart-bg">
              <Pie
                data={data}
                width={200}
                height={371}
                innerRadius={60}
                outerRadius={100}
              />
              <div className="pie-btnstyle">
                <button onClick={changeData}>Transform</button>
              </div>
            </div>
          </Col>

          <Col md="8">
            <BarChart />
          </Col>
        </Row>

        <Row>
          <Col md="7">
            <Table striped>
              <thead>
                <tr>
                  <th>Center Name</th>
                  <th>Best In</th>
                  <th>Worst In</th>
                </tr>
              </thead>
              <tbody>
                <tr>

                  <td>Bengaluru</td>
                  <td>Cleanliness</td>
                  <td>Quick Response</td>
                </tr>
                <tr>

                  <td>Mumbai</td>
                  <td>Cleanliness</td>
                  <td>Facilities</td>
                </tr>
                <tr>

                  <td>Delhi</td>
                  <td>Treatment</td>
                  <td>Cleanliness</td>
                </tr>
                <tr>
                  <td>Chennai</td>
                  <td>Service</td>
                  <td>Management temper</td>
                </tr>
                <tr>
                  <td>Hydrabad</td>
                  <td>Recreation</td>
                  <td>Treatment</td>
                </tr>
              </tbody>
            </Table>
          </Col>


          <Col md="5">
            <Card>




            </Card>
          </Col>

        </Row>
      </Container>

    </div>
  );
}