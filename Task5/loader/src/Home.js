import React, {useState, useEffect} from 'react';

let bool = false;

const Home = () => {
    const [file, setFile] = useState(null);
    const [isDragging, setIsDragging] = useState(false);
    const [springValue, setSpringValue] = useState('');

    useEffect(() => {
        fetchSpringValue().then();
    }, []);

    const fetchSpringValue = async () => {
        try {
            const response = await fetch('http://localhost:8080/bruh/track');
            if (response.ok) {
                const springValue = await response.text();
                if (springValue === "") {
                    bool = false;
                    setSpringValue("Comparable track doesnt exists in database");
                } else {
                    bool = true;
                    setSpringValue("Comparable track already exists in database");
                }
            } else console.error('Failed to fetch Spring value.');
        } catch (error) {
            console.error('Error during fetch:', error);
        }
    };

    const handleFileChange = (e) => {
        const selectedFile = e.target.files[0];
        setFile(selectedFile);
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await fetch('http://localhost:8080/bruh/track', {
                method: 'POST', body: formData,
            });

            if (response.ok) {
                console.log('File uploaded successfully!');
                response.text().then(function (text) {
                    if (text === "Ready to go!") {
                        if (bool) {
                            window.location.href = `/compare?file=${encodeURIComponent(file.name)}`;
                        } else {
                            bool = true;
                            setSpringValue("Comparable track already exists in database");
                            setFile(null);
                            document.getElementsByClassName("file-name")[0].innerHTML = "";
                        }
                    } else if (text === "File already uploaded") {
                        alert("Change filename and try again")
                        setFile(null);
                        document.getElementsByClassName("file-name")[0].innerHTML = "";
                    }
                });

            } else {
                console.error('File upload failed.');
            }
        } catch (error) {
            console.error('Error during file upload:', error);
        }
    };

    const handleDragOver = (e) => {
        e.preventDefault();
        setIsDragging(true);
    };

    const handleDragLeave = () => {
        setIsDragging(false);
    };

    const handleDrop = (e) => {
        e.preventDefault();
        setIsDragging(false);

        const droppedFile = e.dataTransfer.files[0];
        setFile(droppedFile);
    };

    return (<div>
        <h1>Home</h1>
        <div className={`container ${isDragging ? 'dragging' : ''}`}>
            <div
                className={`upload-container ${isDragging ? 'active' : ''}`}
                onDragOver={handleDragOver}
                onDragLeave={handleDragLeave}
                onDrop={handleDrop}
            >
                <form onSubmit={handleFormSubmit} encType="multipart/form-data">
                    <h2>Upload KML or GPX File</h2>
                    <p>{springValue}</p>
                    <label className="upload-label" htmlFor="file-upload">
                        Choose File
                    </label>
                    <input
                        type="file"
                        id="file-upload"
                        className="upload-input"
                        name="file"
                        accept=".kml,.gpx"
                        onChange={handleFileChange}
                        required
                    />
                    <div className="file-name">{file ? `File: ${file.name}` : ''}</div>
                    <button type="submit" className="custom-button">
                        Upload
                    </button>
                </form>
            </div>
        </div>
    </div>);
};

export default Home;
