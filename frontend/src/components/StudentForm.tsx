import { useState, useEffect } from 'react';
import type { Student } from '../types/Student';
import { EnrollmentStatus } from '../types/Student';

/**
 * Props for StudentForm component
 */
interface StudentFormProps {
  student: Student | null;
  onSubmit: (student: Partial<Student>) => void;
  onCancel: () => void;
}

/**
 * StudentForm Component
 * Form for creating new students or editing existing ones.
 * 
 * Features:
 * - Input validation
 * - Pre-fills form when editing
 * - Responsive design
 * - Clear visual feedback
 */
const StudentForm: React.FC<StudentFormProps> = ({ student, onSubmit, onCancel }) => {
  const [formData, setFormData] = useState<Partial<Student>>({
    firstName: '',
    lastName: '',
    email: '',
    dateOfBirth: '',
    phoneNumber: '',
    address: '',
    major: '',
    gpa: undefined,
    enrollmentStatus: EnrollmentStatus.ACTIVE,
  });

  // Pre-fill form when editing an existing student
  useEffect(() => {
    if (student) {
      setFormData(student);
    } else {
      // Reset form for new student
      setFormData({
        firstName: '',
        lastName: '',
        email: '',
        dateOfBirth: '',
        phoneNumber: '',
        address: '',
        major: '',
        gpa: undefined,
        enrollmentStatus: EnrollmentStatus.ACTIVE,
      });
    }
  }, [student]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'gpa' ? (value ? parseFloat(value) : undefined) : value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Sanitize form data before submission
    const sanitizedData: Partial<Student> = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      dateOfBirth: formData.dateOfBirth,
      major: formData.major,
      enrollmentStatus: formData.enrollmentStatus || EnrollmentStatus.ACTIVE,
    };

    // Only include optional fields if they have values
    if (formData.phoneNumber && formData.phoneNumber.trim()) {
      sanitizedData.phoneNumber = formData.phoneNumber.trim();
    }
    if (formData.address && formData.address.trim()) {
      sanitizedData.address = formData.address.trim();
    }
    if (formData.gpa !== undefined && formData.gpa !== null) {
      sanitizedData.gpa = formData.gpa;
    }

    console.log('Submitting student data:', sanitizedData);
    onSubmit(sanitizedData);
  };

  return (
    <div className="bg-white shadow-lg rounded-lg p-6 mb-8">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">
        {student ? 'Edit Student' : 'Add New Student'}
      </h2>
      <form onSubmit={handleSubmit}>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* First Name */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              First Name <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="John"
            />
          </div>

          {/* Last Name */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Last Name <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Doe"
            />
          </div>

          {/* Email */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Email <span className="text-red-500">*</span>
            </label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="john.doe@example.com"
            />
          </div>

          {/* Date of Birth */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Date of Birth <span className="text-red-500">*</span>
            </label>
            <input
              type="date"
              name="dateOfBirth"
              value={formData.dateOfBirth}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
            />
          </div>

          {/* Phone Number */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Phone Number
            </label>
            <input
              type="tel"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="1234567890"
              pattern="[0-9]{10,15}"
            />
          </div>

          {/* Major */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Major <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              name="major"
              value={formData.major}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="Computer Science"
            />
          </div>

          {/* GPA */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              GPA
            </label>
            <input
              type="number"
              name="gpa"
              value={formData.gpa || ''}
              onChange={handleChange}
              step="0.01"
              min="0"
              max="4"
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="3.5"
            />
          </div>

          {/* Enrollment Status */}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Enrollment Status <span className="text-red-500">*</span>
            </label>
            <select
              name="enrollmentStatus"
              value={formData.enrollmentStatus}
              onChange={handleChange}
              required
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
            >
              <option value={EnrollmentStatus.ACTIVE}>Active</option>
              <option value={EnrollmentStatus.INACTIVE}>Inactive</option>
              <option value={EnrollmentStatus.GRADUATED}>Graduated</option>
              <option value={EnrollmentStatus.SUSPENDED}>Suspended</option>
            </select>
          </div>

          {/* Address - Full Width */}
          <div className="md:col-span-2">
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Address
            </label>
            <textarea
              name="address"
              value={formData.address}
              onChange={handleChange}
              rows={3}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              placeholder="123 Main St, City, State"
            />
          </div>
        </div>

        {/* Form Actions */}
        <div className="flex justify-end space-x-4 mt-6">
          <button
            type="button"
            onClick={onCancel}
            className="px-6 py-2 border border-gray-300 rounded-lg text-gray-700 hover:bg-gray-50 transition-colors"
          >
            Cancel
          </button>
          <button
            type="submit"
            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
          >
            {student ? 'Update Student' : 'Add Student'}
          </button>
        </div>
      </form>
    </div>
  );
};

export default StudentForm;
