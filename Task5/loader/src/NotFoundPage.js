import React from "react";

function NotFoundPage() {
    return (
        <div>
            <h1>Page Not Found</h1>
            <p>This page cannot be found. Try again later or go to "Home" page</p>
            <a href="/home" className="custom-button">Home</a>
        </div>
    );

}

export default NotFoundPage;