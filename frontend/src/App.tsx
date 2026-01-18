import { useState, useEffect } from 'react';
import StudentList from './components/StudentList';
import StudentForm from './components/StudentForm';
import studentService from './services/studentService';
import { Student } from './types/Student';
import './App.css';

/**
 * Main App Component
 * Root component for the Student Management System frontend.
 * 
 * Features:
 * - Fetch and display all students
 * - Create new students
 * - Update existing students
 * - Delete students
 * - Show/hide form for adding/editing
 * - Error handling and user feedback
 */
function App() {
  const [students, setStudents] = useState<Student[]>([]);
  const [selectedStudent, setSelectedStudent] = useState<Student | null>(null);
  const [showForm, setShowForm] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  // Fetch all students on component mount
  useEffect(() => {
    fetchStudents();
  }, []);

  /**
   * Fetch all students from the backend
   */
  const fetchStudents = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await studentService.getAllStudents();
      setStudents(data);
    } catch (err) {
      setError('Failed to fetch students. Make sure the backend is running on http://localhost:8080');
      console.error('Error fetching students:', err);
    } finally {
      setLoading(false);
    }
  };

  /**
   * Handle form submission for creating or updating a student
   */
  const handleSubmit = async (studentData: Partial<Student>) => {
    try {
      setError(null);
      if (selectedStudent && selectedStudent.id) {
        // Update existing student
        await studentService.updateStudent(selectedStudent.id, studentData);
        showSuccess('Student updated successfully!');
      } else {
        // Create new student
        await studentService.createStudent(studentData);
        showSuccess('Student created successfully!');
      }
      await fetchStudents();
      handleCancel();
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to save student. Please check your input and try again.');
      console.error('Error saving student:', err);
    }
  };

  /**
   * Handle student deletion
   */
  const handleDelete = async (id: number) => {
    try {
      setError(null);
      await studentService.deleteStudent(id);
      showSuccess('Student deleted successfully!');
      await fetchStudents();
    } catch (err) {
      setError('Failed to delete student. Please try again.');
      console.error('Error deleting student:', err);
    }
  };

  /**
   * Handle edit button click
   */
  const handleEdit = (student: Student) => {
    setSelectedStudent(student);
    setShowForm(true);
    setError(null);
  };

  /**
   * Handle cancel button - close form and reset state
   */
  const handleCancel = () => {
    setShowForm(false);
    setSelectedStudent(null);
    setError(null);
  };

  /**
   * Show success message temporarily
   */
  const showSuccess = (message: string) => {
    setSuccessMessage(message);
    setTimeout(() => setSuccessMessage(null), 3000);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100">
      <div className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="text-center mb-8">
          <h1 className="text-4xl font-bold text-gray-800 mb-2">
            Student Management System
          </h1>
          <p className="text-gray-600">
            Manage student records with full CRUD operations
          </p>
        </div>

        {/* Success Message */}
        {successMessage && (
          <div className="mb-6 p-4 bg-green-100 border border-green-400 text-green-700 rounded-lg animate-fade-in">
            <p className="font-medium">{successMessage}</p>
          </div>
        )}

        {/* Error Message */}
        {error && (
          <div className="mb-6 p-4 bg-red-100 border border-red-400 text-red-700 rounded-lg">
            <p className="font-medium">{error}</p>
          </div>
        )}

        {/* Add Student Button */}
        {!showForm && (
          <div className="mb-6">
            <button
              onClick={() => setShowForm(true)}
              className="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors shadow-md hover:shadow-lg"
            >
              + Add New Student
            </button>
          </div>
        )}

        {/* Student Form */}
        {showForm && (
          <StudentForm
            student={selectedStudent}
            onSubmit={handleSubmit}
            onCancel={handleCancel}
          />
        )}

        {/* Student List */}
        <StudentList
          students={students}
          onEdit={handleEdit}
          onDelete={handleDelete}
          loading={loading}
        />

        {/* Footer */}
        <div className="mt-8 text-center text-gray-600">
          <p className="text-sm">
            Backend API: <span className="font-mono text-blue-600">http://localhost:8080/api/students</span>
          </p>
          <p className="text-xs mt-2">
            Full-Stack CRUD Application | React + TypeScript + Spring Boot
          </p>
        </div>
      </div>
    </div>
  );
}

export default App;
