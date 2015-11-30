function max = myMaxSubstr(S, T)
L=zeros(length(S), length(T));
u = 1; v = 1;
for i = 1:length(S)
    for j = 1:length(T)
        if S(i) == T(j)
            L(i+1,j+1) = L(i,j) + 1;
            if (L(i + 1, j + 1) > L(u, v))                 
               u = i + 1;
               v = j + 1;
            end
        end
    end
    max = L;
end
            
            