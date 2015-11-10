s = 1000;
A = randi(s,s);
B = randi(s,s);
tic
C = zeros(s,s);
for i = 1:length(A)
    for j = 1:length(A)
       C(i,j) = C(i,j) + A(i,:)*B(:,j);
    end 
end
toc
tic
D = zeros(s,s);
parfor i = 1:length(A)
    D(i,:) = D(i,:) + A(i,:)*B(:,:); 
end
toc

tic;F=A*B;toc
isequal(D,F)
