import React, {useState, useEffect} from 'react';

function Compare() {
    const [data, setData] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            // Extracting the file parameter from the URL
            const params = new URLSearchParams(window.location.search);
            const fileParam = params.get('file');

            if (!fileParam) {
                console.error('File parameter is missing in the URL.');
                return;
            }

            // Constructing the URL with the file parameter
            const url = `http://localhost:8080/bruh/track/compare?file=${encodeURIComponent(fileParam)}`;

            const response = await fetch(url);

            if (response.ok) {
                const result = await response.text();
                setData(result);
            } else {
                console.error('Failed to fetch data from Spring Boot.');
            }
        } catch (error) {
            console.error('Error during fetch:', error);
        }
    };

    return (
        <div>
            <h1>Compare</h1>
            <div className={`container`}>
                {data ? (
                    <div>
                        <pre>{data}</pre>
                    </div>
                ) : (
                    <p>Loading data...</p>
                )}
            </div>
            <a href="/home" className="custom-button">Home</a>
        </div>
    );
}

export default Compare;
