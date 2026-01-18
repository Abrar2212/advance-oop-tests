import type { Student } from '../types/Student';

/**
 * Props for StudentList component
 */
interface StudentListProps {
  students: Student[];
  onEdit: (student: Student) => void;
  onDelete: (id: number) => void;
  loading: boolean;
}

/**
 * StudentList Component
 * Displays a table of all students with action buttons.
 * 
 * Features:
 * - Responsive table design
 * - Edit and Delete actions
 * - Loading state
 * - Empty state message
 */
const StudentList: React.FC<StudentListProps> = ({ students, onEdit, onDelete, loading }) => {
  if (loading) {
    return (
      <div className="flex justify-center items-center p-8">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  if (students.length === 0) {
    return (
      <div className="text-center p-8 bg-gray-50 rounded-lg">
        <p className="text-gray-500 text-lg">No students found. Add your first student!</p>
      </div>
    );
  }

  return (
    <div className="overflow-x-auto shadow-md rounded-lg">
      <table className="min-w-full bg-white">
        <thead className="bg-gradient-to-r from-blue-600 to-blue-700 text-white">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">ID</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Name</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Email</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Major</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">GPA</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Status</th>
            <th className="px-6 py-3 text-left text-xs font-medium uppercase tracking-wider">Actions</th>
          </tr>
        </thead>
        <tbody className="divide-y divide-gray-200">
          {students.map((student) => (
            <tr key={student.id} className="hover:bg-gray-50 transition-colors">
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                {student.id}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {student.firstName} {student.lastName}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {student.email}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {student.major}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {student.gpa?.toFixed(2) || 'N/A'}
              </td>
              <td className="px-6 py-4 whitespace-nowrap">
                <span className={`px-2 inline-flex text-xs leading-5 font-semibold rounded-full
                  ${student.enrollmentStatus === 'ACTIVE' ? 'bg-green-100 text-green-800' : ''}
                  ${student.enrollmentStatus === 'INACTIVE' ? 'bg-gray-100 text-gray-800' : ''}
                  ${student.enrollmentStatus === 'GRADUATED' ? 'bg-blue-100 text-blue-800' : ''}
                  ${student.enrollmentStatus === 'SUSPENDED' ? 'bg-red-100 text-red-800' : ''}
                `}>
                  {student.enrollmentStatus}
                </span>
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm font-medium space-x-2">
                <button
                  onClick={() => onEdit(student)}
                  className="text-blue-600 hover:text-blue-900 hover:underline transition-colors"
                >
                  Edit
                </button>
                <button
                  onClick={() => {
                    if (window.confirm(`Are you sure you want to delete ${student.firstName} ${student.lastName}?`)) {
                      onDelete(student.id!);
                    }
                  }}
                  className="text-red-600 hover:text-red-900 hover:underline transition-colors"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StudentList;
