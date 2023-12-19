import React from 'react';
import {Route, Routes} from "react-router-dom";
import Home from "./Home";
import Compare from "./Compare";
import NotFoundPage from "./NotFoundPage";

function App() {

    return (
        <Routes>
            <Route path="/home" element={<Home/>}/>
            <Route path="/compare" element={<Compare/>}/>
            <Route path="*" element={<NotFoundPage/>}/>
        </Routes>
    );
}

export default App;