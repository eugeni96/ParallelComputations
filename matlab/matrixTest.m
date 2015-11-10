n = 5000;
A = randi(n,n);
B = randi(n,n);
a = gpuArray(A);
b = gpuArray(B);
% tic
% C = zeros(n,n);
% for i = 1:length(A)
%        C(i,:) = C(i,:) + A(i,:)*B(:,:); 
% end
% toc
% 
% tic; F = A*B; toc;
% isequal(F,C)
tic; v = a*b; toc;