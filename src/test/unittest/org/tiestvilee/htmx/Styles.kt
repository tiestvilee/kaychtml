package unittest.org.tiestvilee.htmx

val minMobileWidth = 320
val minTabletWidth = 480
val minDisplayWidth = 768

private val headerBlue = """#4C8BF5"""

val styles = """
    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
    
    * {
        box-sizing: border-box;
    }  

    body {
        font-family: 'Inter', sans-serif;
        align-items: center;
        justify-content: center;
    }

    main {
        margin: 0 auto;
        max-width: 570px;
        width: 100%;
        background: white;
    }
    
    main > * {
        padding: 20px;
        margin: 20px;
        border: 1px solid lightgrey;
        display: block;
    }
    
    section > * {
        width: 100%;
    }
    
    td, th {
        padding: 0.5em;
    }
    
    th {
        background-color: $headerBlue;
        color: #fff;
    }    
    
    tbody tr:nth-child(odd) {
        background-color: #f0f0f0;
    }
    
    table button {
        padding: 0.5em 1em;
    }

    form {
    }

    form > * {
        margin-bottom: 15px;
        display: block;
    }

    columns {
        display: flex;
        gap: 20px;
        flex-wrap: wrap;
    }

    columns > div {
        flex-grow: 1;
        flex-basis: ${minTabletWidth/2}px;
    }

    h2 {
        font-weight: 600;
        font-size: 28px;
        line-height: 34px;
        color: #07074d;
    }

    p {
        font-size: 16px;
        line-height: 24px;
        color: #536387;
        margin-top: 12px;
    }

    label {
        color: #536387;
        font-size: 14px;
        line-height: 24px;
        display: block;
        margin-bottom: 10px;
        user-select: none;
    }

    a {
        color: $headerBlue;
    }

    input[type=text], input[type=email], input[type=password] {
        text-align: center;
        width: 100%;
        padding: 13px 22px;
        border-radius: 5px;
        border: 1px solid #dde3ec;
        background: #ffffff;
        font-weight: 500;
        font-size: 16px;
        color: #536387;
        outline: none;
        resize: none;
    }

    input[type=text]:focus, input[type=email]:focus, input[type=password]:focus {
        border-color: #6a64f1;
        box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.05);
    }

    input[type="checkbox"] {
        margin: 0.3em
    }

    button {
        font-size: 16px;
        border-radius: 5px;
        padding: 14px 25px;
        border: none;
        font-weight: 500;
        background-color: $headerBlue;
        color: white;
        cursor: pointer;
    }

    button:hover {
        box-shadow: 0px 3px 8px rgba(0, 0, 0, 0.1);
    }
"""