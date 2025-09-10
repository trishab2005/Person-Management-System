import React, { useState, useEffect } from 'react';
import axios from 'axios';

const API_URL = 'http://localhost:8888/api/persons'; // Your Spring Boot API endpoint

function Person() {
    const [persons, setPersons] = useState([]);
    const [newPerson, setNewPerson] = useState({ name: '', age: '', aadharCard: { aadharNumber: '', address: '' } });
    const [editingPersonId, setEditingPersonId] = useState(null);

    useEffect(() => {
        fetchPersons();
    }, []);

    const fetchPersons = async () => {
        try {
            const response = await axios.get(API_URL);
            setPersons(response.data);
        } catch (error) {
            console.error('There was an error fetching the persons!', error);
        }
    };

    const handleCreatePerson = async (event) => {
        event.preventDefault();
        try {
            await axios.post(API_URL, newPerson);
            setNewPerson({ name: '', age: '', aadharCard: { aadharNumber: '', address: '' } }); // Clear form
            fetchPersons(); // Refresh the list
        } catch (error) {
            console.error('There was an error creating the person!', error);
        }
    };

    const handleDeletePerson = async (id) => {
        try {
            await axios.delete(`${API_URL}/${id}`);
            fetchPersons(); // Refresh the list
        } catch (error) {
            console.error('There was an error deleting the person!', error);
        }
    };

    const handleUpdatePerson = async (id) => {
        try {
            // Find the person being edited from the state
            const personToUpdate = persons.find(p => p.id === id);
            await axios.put(`${API_URL}/${id}`, personToUpdate);
            setEditingPersonId(null); // Exit edit mode
            fetchPersons(); // Refresh the list
        } catch (error) {
            console.error('There was an error updating the person!', error);
        }
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        // Check if the input belongs to aadharCard nested object
        if (name === 'aadharNumber' || name === 'address') {
            setNewPerson({
                ...newPerson,
                aadharCard: {
                    ...newPerson.aadharCard,
                    [name]: value
                }
            });
        } else {
            setNewPerson({ ...newPerson, [name]: value });
        }
    };

    const handleEditInputChange = (event, id) => {
        const { name, value } = event.target;
        setPersons(prevPersons =>
            prevPersons.map(person =>
                person.id === id ? {
                    ...person,
                    // Handle nested AadharCard fields
                    ...(name === 'aadharNumber' || name === 'address' ?
                            { aadharCard: { ...person.aadharCard, [name]: value } } :
                            { [name]: value }
                    )
                } : person
            )
        );
    };

    return (
        <div>
            <h1>Person Management System</h1>
            {/* Create Form */}
            <h2>Add a New Person</h2>
            <form onSubmit={handleCreatePerson}>
                <input
                    type="text"
                    name="name"
                    placeholder="Name"
                    value={newPerson.name}
                    onChange={handleInputChange}
                />
                <input
                    type="number"
                    name="age"
                    placeholder="Age"
                    value={newPerson.age}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="aadharNumber"
                    placeholder="Aadhar Number"
                    value={newPerson.aadharCard.aadharNumber}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="address"
                    placeholder="Address"
                    value={newPerson.aadharCard.address}
                    onChange={handleInputChange}
                />
                <button type="submit">Add Person</button>
            </form>
            {/* Persons List */}
            <h2>All Persons</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Aadhar Number</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {persons.map((person) => (
                    <tr key={person.id}>
                        <td>
                            {editingPersonId === person.id ? (
                                <input
                                    type="text"
                                    name="name"
                                    value={person.name}
                                    onChange={(e) => handleEditInputChange(e, person.id)}
                                />
                            ) : (
                                person.name
                            )}
                        </td>
                        <td>
                            {editingPersonId === person.id ? (
                                <input
                                    type="number"
                                    name="age"
                                    value={person.age}
                                    onChange={(e) => handleEditInputChange(e, person.id)}
                                />
                            ) : (
                                person.age
                            )}
                        </td>
                        <td>
                            {editingPersonId === person.id ? (
                                <input
                                    type="text"
                                    name="aadharNumber"
                                    value={person.aadharCard?.aadharNumber || ''}
                                    onChange={(e) => handleEditInputChange(e, person.id)}
                                />
                            ) : (
                                person.aadharCard?.aadharNumber || 'N/A'
                            )}
                        </td>
                        <td>
                            {editingPersonId === person.id ? (
                                <input
                                    type="text"
                                    name="address"
                                    value={person.aadharCard?.address || ''}
                                    onChange={(e) => handleEditInputChange(e, person.id)}
                                />
                            ) : (
                                person.aadharCard?.address || 'N/A'
                            )}
                        </td>
                        <td>
                            {editingPersonId === person.id ? (
                                <button onClick={() => handleUpdatePerson(person.id)}>Save</button>
                            ) : (
                                <button onClick={() => setEditingPersonId(person.id)}>Edit</button>
                            )}
                            <button onClick={() => handleDeletePerson(person.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Person;