
import { useState, useRef } from "react";
import { cn } from "@/lib/utils";
import { Upload, File, CheckCircle2, AlertCircle, X } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from "@/components/ui/tooltip";
import { toast } from "@/components/ui/sonner";

interface FileUploadProps {
  label: string;
  description: string;
  acceptedFileTypes?: string;
  maxFileSizeMB?: number;
  onChange?: (file: File | null) => void;
}

export function FileUpload({
  label,
  description,
  acceptedFileTypes = ".pdf,.doc,.docx,.xls,.xlsx,.csv",
  maxFileSizeMB = 10,
  onChange,
}: FileUploadProps) {
  const [file, setFile] = useState<File | null>(null);
  const [isDragging, setIsDragging] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  const handleFileChange = (selectedFile: File | null) => {
    if (!selectedFile) {
      setFile(null);
      setError(null);
      if (onChange) onChange(null);
      return;
    }

    // Check file type
    const fileExtension = `.${selectedFile.name.split('.').pop()?.toLowerCase()}`;
    const isValidType = acceptedFileTypes.includes(fileExtension);
    
    // Check file size
    const isValidSize = selectedFile.size <= maxFileSizeMB * 1024 * 1024;

    if (!isValidType) {
      setError(`Invalid file type. Please upload ${acceptedFileTypes.replace(/,/g, ', ')}`);
      setFile(null);
      if (onChange) onChange(null);
      return;
    }

    if (!isValidSize) {
      setError(`File size exceeds ${maxFileSizeMB}MB`);
      setFile(null);
      if (onChange) onChange(null);
      return;
    }

    setFile(selectedFile);
    setError(null);
    if (onChange) onChange(selectedFile);
  };

  const handleDragOver = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(true);
  };

  const handleDragLeave = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(false);
  };

  const handleDrop = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    setIsDragging(false);

    if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
      handleFileChange(e.dataTransfer.files[0]);
    }
  };

  const handleRemoveFile = () => {
    setFile(null);
    setError(null);
    if (inputRef.current) inputRef.current.value = "";
    if (onChange) onChange(null);
  };

  return (
    <div className="mb-6">
      <div className="flex justify-between items-center mb-2">
        <label className="block text-sm font-medium text-gray-700">
          {label}
        </label>
        <TooltipProvider>
          <Tooltip>
            <TooltipTrigger asChild>
              <div className="cursor-help text-gray-400 hover:text-gray-600">
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="16"
                  height="16"
                  viewBox="0 0 24 24"
                  fill="none"
                  stroke="currentColor"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                >
                  <circle cx="12" cy="12" r="10" />
                  <path d="M12 16v-4" />
                  <path d="M12 8h.01" />
                </svg>
              </div>
            </TooltipTrigger>
            <TooltipContent side="top">
              <p className="max-w-xs text-xs">{description}</p>
            </TooltipContent>
          </Tooltip>
        </TooltipProvider>
      </div>

      {!file ? (
        <div
          className={cn(
            "border-2 border-dashed rounded-lg p-6 text-center cursor-pointer transition-colors",
            isDragging
              ? "border-blue-400 bg-blue-50"
              : error
              ? "border-red-300 bg-red-50"
              : "border-gray-300 hover:border-gray-400 bg-gray-50 hover:bg-gray-100"
          )}
          onDragOver={handleDragOver}
          onDragLeave={handleDragLeave}
          onDrop={handleDrop}
          onClick={() => inputRef.current?.click()}
        >
          <div className="flex flex-col items-center justify-center">
            <Upload className="h-10 w-10 text-gray-400 mb-3" />
            <p className="text-sm text-gray-600 mb-1">
              <span className="font-semibold">Click to upload</span> or drag and
              drop
            </p>
            <p className="text-xs text-gray-500">
              {acceptedFileTypes.replace(/,/g, ', ')} (Max: {maxFileSizeMB}MB)
            </p>
          </div>
          <input
            type="file"
            className="hidden"
            accept={acceptedFileTypes}
            ref={inputRef}
            onChange={(e) =>
              handleFileChange(e.target.files ? e.target.files[0] : null)
            }
          />
        </div>
      ) : (
        <div className="border rounded-lg p-4 bg-gray-50">
          <div className="flex items-center justify-between">
            <div className="flex items-center space-x-3">
              <div className="p-2 bg-blue-100 rounded">
                <File className="h-5 w-5 text-blue-600" />
              </div>
              <div>
                <p className="text-sm font-medium text-gray-700 truncate max-w-[200px]">
                  {file.name}
                </p>
                <p className="text-xs text-gray-500">
                  {(file.size / 1024 / 1024).toFixed(2)} MB
                </p>
              </div>
            </div>
            <div className="flex items-center space-x-2">
              <div className="text-green-600">
                <CheckCircle2 className="h-5 w-5" />
              </div>
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  handleRemoveFile();
                }}
                className="text-gray-500 hover:text-red-500 transition-colors"
              >
                <X className="h-5 w-5" />
              </button>
            </div>
          </div>
        </div>
      )}
      
      {error && (
        <div className="mt-2 flex items-center text-sm text-red-600">
          <AlertCircle className="h-4 w-4 mr-1" />
          {error}
        </div>
      )}
    </div>
  );
}
